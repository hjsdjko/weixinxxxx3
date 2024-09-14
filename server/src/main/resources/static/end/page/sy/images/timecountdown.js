/* Copyright (c) 2014 xcsoft.cn(http://www.xcsoft.cn)
 * Author:Xiucheng Yang
 * Version: 4.0
 * MyQQ:549642084
 * LastUpdateTime:2015-11-03
 * Used Url:http://www.xcsoft.cn/article/479.html
 */
if (typeof xcsoft == "undefined") var xcsoft = new
function() {};
xcsoft.countdown = function(a, b, c) {
    var d = new Object();
    var e = 0;
    if (typeof a == "object") {
        var f = parseInt(new Date().getTime() / 1000);
        var g = a.startTime ? parseInt(a.startTime) : 0;
        g = g == 0 ? f: g;
        var h = a.endTime;
        var x = g - f;
        d.decimal = parseInt(a.msec ? a.msec: 0)
    } else {
        var f = 0;
        var x = 0;
        d.decimal = 0;
        var h = a;
        var i = h.toString();
        if (i.indexOf('.') > 0) {
            d.decimal = i.split('.')[1];
            if (d.decimal > 3) {
                d.decimal = 3
            }
        }
    }
    d.time = h;
    d.finish = false;
    if (isNaN(h)) {
        var j = h.substring(0, 19);
        j = j.replace(/-/g, '/');
        d.time = new Date(j).getTime() / 1000
    }
    var k = d.decimal == 0 ? 100 : 100;
    d.day = 0;
    d.hour = 0;
    d.minute = 0;
    d.second = 0;
    d.t = setInterval(function() {
        e = new Date().getTime();
        f = parseInt(e / 1000 + x);
        var l = d.time - f;
        if (l <= 0) {
            e = 0;
            l = 0
        }
        if (l >= 0) {
            d = xcsoft.getTimeObject(d, l)
        }
        if (d.decimal == 1) {
            d.msecZero = d.msec = parseInt(10 - e % 1000 / 100);
            if (d.msec == 10 || l == 0) {
                d.msecZero = d.msec = 0
            }
        } else if (d.decimal == 2) {
            d.msecZero = d.msec = parseInt(100 - e % 1000 / 10);
            if (d.msec < 10) {
                d.msecZero = '0' + d.msec
            } else if (d.msec == 100 || l == 0) {
                d.msec = 0;
                d.msecZero = '0' + d.msec
            }
        } else {
            d.msecZero = d.msec = parseInt(1000 - e % 1000);
            if (d.msec < 10) {
                d.msecZero = '00' + d.msec
            } else if (d.msec < 100) {
                d.msecZero = '0' + d.msec
            } else if (d.msec == 1000 || l == 0) {
                d.msec = 0;
                d.msecZero = '00' + d.msec
            }
        }
        if (b) {
            b(d)
        }
        if (l <= 0 || d.stop == true) {
            clearInterval(d.t);
            d.finish = true;
            if (c) c(d)
        }
    },
    k)
};
xcsoft.countup = function(c, d) {
    if (!c) {
        var e = new Date().getTime();
        c = parseInt(e / 1000)
    } else if (isNaN(c)) {
        c = c.replace(/-/g, '/');
        var e = new Date(c).getTime();
        c = parseInt(e / 1000)
    }
    var f = new Object();
    f.time = c;
    f.t = setInterval(function() {
        var a = new Date().getTime();
        nowtime = parseInt(a / 1000);
        var l = nowtime - c;
        f = xcsoft.getTimeObject(f, l);
        if (d) var b = d(f);
        if (b === true) {
            clearTimeout(f.t)
        }
    },
    100)
};
xcsoft.getTimeObject = function(a, l) {
    var b = 60;
    var c = b * b;
    var d = 24 * c;
    a.days = Math.floor(l / d);
    a.year = Math.floor(a.days / 365);
    a.day = Math.floor(a.days % 365);
    a.hour = Math.floor(l % d / c);
    a.minute = Math.floor((l - (a.days * d + a.hour * c)) / b);
    a.second = Math.floor(l % (b));
    a.dayZero = a.day < 10 ? '0' + a.day: a.day;
    a.daysZero = a.days < 10 ? '0' + a.days: a.days;
    a.hourZero = a.hour < 10 ? '0' + a.hour: a.hour;
    a.minuteZero = a.minute < 10 ? '0' + a.minute: a.minute;
    a.secondZero = a.second < 10 ? '0' + a.second: a.second;
    return a
}