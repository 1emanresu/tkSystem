// pages/home/audit/auditInfo/auditInfo.js
var app = getApp();
Page({

  /**
   * 页面的初始数据 
   */
  data: {
    tk_plan_detail_title:"",
    tk_good_apply_name: "",
    tk_good_apply_good_amount: "",
    tk_good_apply_remark: "",
    tk_good_apply_id:"",
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    var tk_good_apply_id = options.tk_good_apply_id;
    console.log("%%%%%%" + tk_good_apply_id );
    //物料申请审核记录
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getGoodsByManageDetail',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tk_good_apply_id: tk_good_apply_id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var data = res.data.data;
        that.setData({
          tk_plan_detail_title: data.tk_plan_detail_title,
          tk_good_apply_name: data.tk_good_apply_name,
          tk_good_apply_good_amount: data.tk_good_apply_good_amount,
          tk_good_apply_remark: data.tk_good_apply_remark,
          tk_good_apply_id: tk_good_apply_id
        });
        console.log("%%%%%%~~ "+ JSON.stringify(data));
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    });
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})