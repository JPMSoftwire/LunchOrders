var gulp = require('gulp');
var requireDir = require('require-dir');
var dir = requireDir('./tasks');

gulp.task('default', ['browserify']);
gulp.task('watch', ['watch-browserify']);

