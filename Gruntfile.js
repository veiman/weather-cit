'use strict';
var fs = require('fs');


var useminAutoprefixer = {
    name: 'autoprefixer',
    createConfig: function(context, block) {
        if(block.src.length === 0) {
            return {};
        } else {
            return require('grunt-usemin/lib/config/cssmin').createConfig(context, block) // Reuse cssmins createConfig
        }
    }
};

module.exports = function (grunt) {
    require('load-grunt-tasks')(grunt);
    require('time-grunt')(grunt);

    grunt.initConfig({
        const: {
            // configurable paths
            app: require('./bower.json').appPath || 'app',
            dist: 'src/main/webapp/dist'
        },
        watch: {
            bower: {
                files: ['bower.json'],
                tasks: ['wiredep']
            },
            ngconstant: {
                files: ['Gruntfile.js', 'pom.xml']
            }
        },
        autoprefixer: {
        },
        browserSync: {
            dev: {
                bsFiles: {
                    src : [
                        'src/main/webapp/**/*.html',
                        'src/main/webapp/**/*.json',
                        'src/main/webapp/assets/styles/**/*.css',
                        'src/main/webapp/scripts/**/*.js',
                        'src/main/webapp/assets/images/**/*.{png,jpg,jpeg,gif,webp,svg}',
                        'tmp/**/*.{css,js}'
                    ]
                }
            },
            options: {
                watchTask: true,
                proxy: "localhost:8080"
            }
        },

        clean: {
            dist: {
                files: [{
                    dot: true,
                    src: [
                        '.tmp',
                        '<%= const.dist %>/*',
                        '!<%= const.dist %>/.git*'
                    ]
                }]
            },
            server: '.tmp'
        },
        jshint: {
            options: {
                jshintrc: '.jshintrc'
            },
            all: [
                'Gruntfile.js',
                'src/main/webapp/scripts/app.js',
                'src/main/webapp/scripts/app/**/*.js',
                'src/main/webapp/scripts/components/**/*.js'
            ]
        },
        concat: {
        },
        uglifyjs: {
        },
        rev: {
            dist: {
                files: {
                    src: [
                        '<%= const.dist %>/scripts/**/*.js',
                        '<%= const.dist %>/assets/styles/**/*.css',
                        '<%= const.dist %>/assets/images/**/*.{png,jpg,jpeg,gif,webp,svg}',
                        '<%= const.dist %>/assets/fonts/*'
                    ]
                }
            }
        },
        useminPrepare: {
            html: 'src/main/webapp/**/*.html',
            options: {
                dest: '<%= const.dist %>',
                flow: {
                    html: {
                        steps: {
                            js: ['concat', 'uglifyjs'],
                            css: ['cssmin', useminAutoprefixer] // Let cssmin concat files so it corrects relative paths to fonts and images
                        },
                            post: {}
                        }
                    }
            }
        },
        usemin: {
            html: ['<%= const.dist %>/**/*.html'],
            css: ['<%= const.dist %>/assets/styles/**/*.css'],
            js: ['<%= const.dist %>/scripts/**/*.js'],
            options: {
                assetsDirs: ['<%= const.dist %>', '<%= const.dist %>/assets/styles', '<%= const.dist %>/assets/images', '<%= const.dist %>/assets/fonts'],
                patterns: {
                    js: [
                        [/(assets\/images\/.*?\.(?:gif|jpeg|jpg|png|webp|svg))/gm, 'Update the JS to reference our revved images']
                    ]
                },
                dirs: ['<%= const.dist %>']
            }
        },
        imagemin: {
            dist: {
                files: [{
                    expand: true,
                    cwd: 'src/main/webapp/assets/images',
                    src: '**/*.{jpg,jpeg}',
                    dest: '<%= const.dist %>/assets/images'
                }]
            }
        },
        svgmin: {
            dist: {
                files: [{
                    expand: true,
                    cwd: 'src/main/webapp/assets/images',
                    src: '**/*.svg',
                    dest: '<%= const.dist %>/assets/images'
                }]
            }
        },
        cssmin: {
        },
        ngtemplates:    {
            dist: {
                cwd: 'src/main/webapp',
                src: ['scripts/app/**/*.html', 'scripts/components/**/*.html',],
                dest: '.tmp/templates/templates.js',
                options: {
                    module: 'weatherAggregator',
                    usemin: 'scripts/app.js',
                    htmlmin:  {
                        removeCommentsFromCDATA: true,
                        collapseWhitespace: true,
                        collapseBooleanAttributes: true,
                        conservativeCollapse: true,
                        removeAttributeQuotes: true,
                        removeRedundantAttributes: true,
                        useShortDoctype: true,
                        removeEmptyAttributes: true
                    }
                }
            }
        },
        htmlmin: {
            dist: {
                options: {
                    removeCommentsFromCDATA: true,
                    collapseWhitespace: true,
                    collapseBooleanAttributes: true,
                    conservativeCollapse: true,
                    removeAttributeQuotes: true,
                    removeRedundantAttributes: true,
                    useShortDoctype: true,
                    removeEmptyAttributes: true,
                    keepClosingSlash: true
                },
                files: [{
                    expand: true,
                    cwd: '<%= const.dist %>',
                    src: ['*.html'],
                    dest: '<%= const.dist %>'
                }]
            }
        },

        copy: {
            fonts: {
                files: [{
                    expand: true,
                    dot: true,
                    flatten: true,
                    cwd: 'src/main/webapp',
                    dest: '<%= const.dist %>/assets/fonts',
                    src: [
                      'bower_components/bootstrap/fonts/*.*'
                    ]
                },
                { // fix for ui-grid
                    expand: true,
                    dot: true,
                    flatten: true,
                    cwd: 'src/main/webapp',
                    dest: '<%= const.dist %>/assets/styles',
                    src: [
                        'bower_components/angular-ui-grid/*.ttf',
                        'bower_components/angular-ui-grid/*.svg',
                        'bower_components/angular-ui-grid/*.eot',
                        'bower_components/angular-ui-grid/*.woff'
                    ]
                }
                ]

            },
            dist: {
                files: [{
                    expand: true,
                    dot: true,
                    cwd: 'src/main/webapp',
                    dest: '<%= const.dist %>',
                    src: [
                        '*.html',
                        'scripts/**/*.html',
                        'assets/images/**/*.{png,gif,webp,jpg,jpeg,svg}',
                        'assets/fonts/*'
                    ]
                }, {
                    expand: true,
                    cwd: '.tmp/assets/images',
                    dest: '<%= const.dist %>/assets/images',
                    src: [
                        'generated/*'
                    ]
                }]
            }
        },
        ngAnnotate: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '.tmp/concat/scripts',
                    src: '*.js',
                    dest: '.tmp/concat/scripts'
                }]
            }
        }


    });

    grunt.registerTask('serve', [
        'clean:server',
        'browserSync',
        'watch'
    ]);

    grunt.registerTask('build', [
        'clean:dist',
        'useminPrepare',
        'ngtemplates',
        'imagemin',
        'svgmin',
        'concat',
        'copy:fonts',
        'copy:dist',
        'ngAnnotate',
        'cssmin',
        'autoprefixer',
        'uglify',
        'rev',
        'usemin',
        'htmlmin'
    ]);

    grunt.registerTask('default', [
        'build'
    ]);
};
