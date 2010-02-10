(function($) {
    $.log = function(message, options) {
        var settings = $.extend({}, $.log.defaults, options);
        var logger = settings.loggers[settings.level];
        if (logger
                && logger.handler
                && logger.priority >= settings.loggers[settings.logLevel].priority) {
            logger.handler(message);
        };
    };
    $.log.defaults = {
        // global logging level
        logLevel: 'debug',
        // log handler name
        level: 'debug',
        // available loggers
        loggers: {
            off: {
                priority: 999,
                handler: function(){}
            },
            debug: {
                priority: 100,
                handler: console.log
            },
            info: {
                priority: 200,
                handler: console.info
            },
            warn: {
                priority: 300,
                handler: console.warn
            },
            error: {
                priority: 400,
                handler: console.error
            }
        }
    };
    // set logging level (by default it's set to 'off')
    $.log.setLogLevel = function(logLevel) {
        if (! $.log.defaults.loggers[logLevel]) {
            throw 'Unknown logging level: ' + logLevel;
        };
        $.log.defaults.logLevel = logLevel;
    };
})(jQuery);