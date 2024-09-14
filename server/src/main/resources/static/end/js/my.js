
function uuid() {
    return 'xxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 8 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(8);
    });
}
function msg(type, msg) {
    Vue.prototype.$message({
        type: type, // success（成功）、warning（警告）, error(错误)
        message: msg,
        duration: 2000,
        offset: 100,
        center: true
    })
}
function personalPage() {
    let user = JSON.parse(localStorage.getItem("user"));
    if (user && user.level && user.level === 1) {
        window.location = '/end/page/accountAdminInfo.html';
    }
    if (user && user.level && user.level === 2) {
        window.location = '/end/page/accountSellerInfo.html';
    }
    if (user && user.level && user.level === 3) {
        window.location = '/end/page/accountUserInfo.html';
    }

    if (user && user.level && user.level === 5) {
        window.location = '/end/page/accountYugongxinxiInfo.html';
    }

}

function logout() {
    axios.get('/logout').then(res => {
        if (res.data.code === '0') {
            window.location = '/end/page/login.html';
        } else {
            alert(res.data.msg);
        }
    })
}
function logout2() {
    axios.get('/logout').then(res => {
        if (res.data.code === '0') {
            window.parent.location = '/end/page/login.html';
        } else {
            alert(res.data.msg);
        }
    })
}
/**
 * 弹出式提示框，默认1.2秒自动消失
 * @param message 提示信息
 * @param style 提示样式，有alert-success、alert-danger、alert-warning、alert-info
 * @param time 消失时间
 */
let prompt = function (message, style, time)
{
    style = (style === undefined) ? 'alert-success' : style;
    time = (time === undefined) ? 1200 : time;
    $('<div>')
        .appendTo('body')
        .addClass('alert ' + style)
        .html(message)
        .show()
        .delay(time)
        .fadeOut();
};

// 成功提示
let success_prompt = function(message, time)
{
    prompt(message, 'alert-success', time);
};

// 失败提示
let fail_prompt = function(message, time)
{
    prompt(message, 'alert-danger', time);
};

// 提醒
let warning_prompt = function(message, time)
{
    prompt(message, 'alert-warning', time);
};

// 信息提示
let info_prompt = function(message, time)
{
    prompt(message, 'alert-info', time);
};