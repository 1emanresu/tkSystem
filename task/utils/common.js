var app = getApp();
/**
 * 对字符串判空
 */
function isStringEmpty(data) {
  if (null == data || "" == data) {
    return true;
  }
  return false;
}
/**
 * 封装网络请求
 */
function sentHttpRequestToServer(uri, data, method, successCallback, failCallback, completeCallback) {
  wx.request({
    url: app.d.hostUrl + uri,
    data: data,
    method: method,
    header: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    success: successCallback,
    fail: failCallback,
    complete: completeCallback
  })
}

/**
 * 弹窗提示成功
 */
function toastSuccess() {
  wx.showToast({
    title: '成功',
    icon: 'success',
    duration: 2000
  })
}
module.exports = {
  toastSuccess: toastSuccess,
  getCode: getCode,
}
 
// 腾讯地图逆向解析地址
function showAddress(latitude, longitude) {
  var that = this;
  var qqMapApi = config.qqMapApi + "?location=" + latitude + ',' +
    longitude + "&key=" + config.qqUserkey + "&get_poi=1";
  wx.request({
    url: qqMapApi,
    data: {},
    method: 'GET',
    success: (res) => {
      console.log(res)
      if (res.statusCode == 200 && res.data.status == 0) {
        wx.setStorageSync('address', res.data.result);
        console.log('   address    ', res.data.result);
        // this.addressShow(res.data.result);
      }
    }
  })
}
// function addressShow(address){

// }
function getCode(_this, num) {
  _this.setData({
    isShow: true
  })
  var remain = num;
  var time = setInterval(function () {
    if (remain == 1) {
      clearInterval(time);
      _this.setData({
        sec: num,
        isShow: false
      })
      return false      //必须有
    }
    remain--;
    _this.setData({
      sec: remain
    })
  }, 1000)
}