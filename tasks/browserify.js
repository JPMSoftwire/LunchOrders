var browserify = require('browserify');
var gulp = require('gulp');
var source = require('vinyl-source-stream');
var del = require('del');
var watchify = require('watchify');
var browserifyCss = require('browserify-css');
var handleError = require('./handle-error');

gulp.task('clean-scripts', function (done) {
    del('./public/javascripts/bundle/js', done);
});

gulp.task('browserify', function() {
    return compileScripts(false);
});

gulp.task('watch-browserify', function () {
    return compileScripts(true);
});

function compileScripts(watchForChanges) {
    var bundler = browserify({
        entries: ['./javascripts/app.js'],
        basedir: './public',
        debug: true,
        cache: {}, packageCache: {}, fullPaths: true
    })
        .on('error', handleError)
        .transform(browserifyCss, {global: true})
        .on('error', handleError);

    if (watchForChanges) {
        bundler = watchify(bundler);
    }

    var bundle = function () {
        return bundler.bundle()
            .pipe(source('bundle.js'))
            .pipe(gulp.dest('./public/javascripts/'));
    };

    bundler.on('update', bundle);

    return bundle();
};