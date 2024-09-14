const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
const addTime = (data,time) => {
  //下面的不是时间戳，是时间戳*1000
  var timestamp = Date.parse(new Date(time));
  var newTimestamp = timestamp + data  * 60 * 60 * 1000;
  var date = new Date(newTimestamp);
  return date
}
module.exports = {
  formatNumber:formatNumber,
  formatTime: formatTime,
  addTime:addTime
}

const customModal = (msg, err) => {
  wx.showModal({
      showCancel: false,
      title: err ? '错误' : '提示',
      content: msg
  })
}