// pages/home/binding/binding.js
var app = getApp();
Page({ 
  /**
   * 页面的初始数据
   */
  data: {
    result:'', 
    infoList:[],
    phoneNum:'',
  },

  unbundle: function (event){
    var that=this;
    /// tk_user_id
      //获取要删除数据的id号
    var id = event.currentTarget.dataset.deleteid;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/deleteEmployee',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        deletetkUserId: id,//人员id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        wx.showToast({
          title: '解绑成功',
          icon: 'loading',
          duration: 1500
        })
        that.selectAll();
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    })
 
  },
  sweep: function () {
    var that = this;
    var show;
    wx.scanCode({
      success: (res) => {
       var show= "结果:" + res.result + "二维码类型:" + res.scanType + "字符集:" + res.charSet + "路径:" + res.path;
       wx.request({
         //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
         url: app.d.hostUrl + 'manager/postEmployee',
         method: 'POST',
         data: {
           tkUserId: app.d.tkUserId,
           tkUserToken: app.d.tkUserToken,
           postUserId:res.result,//人员id
         },
         header: {
           'content-type': 'application/x-www-form-urlencoded'
         },
         success: function (res) {
           var info = res.data.code;
           if (info == 1) {
             wx.showToast({
               title: '绑定成功',
               icon: 'loading',
               duration: 1500
             })
             that.selectAll();
           } else {
             wx.showToast({
               title: '绑定失败',
               icon: 'loading',
               duration: 1500
             })
           }
         },
         fail: function () {
           wx.showToast({
             title: '服务器网络错误!',
             icon: 'loading',
             duration: 1500
           })
         }
       })
      },
      fail: (res) => {
        wx.showToast({
          title: '失败',
          icon: 'success',
          duration: 2000
        })
      },
      complete: (res) => {
      }
    })
  } ,
  //获取用户输入的用户名
  userNameInput: function (e) {
    var that = this;
    //人员信息
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getEmployeeByName',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tkUserName: e.detail.value
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        if (list == null) {
          list = [];
        }
        console.log(JSON.stringify(list));
        that.setData({
          infoList: list,
        })
      },
      fail: function () {
        wx.showToast({
          title: '服务器网络错误!',
          icon: 'loading',
          duration: 1500
        })
      }
    })
  }, 
  scanCode: function () {
    var that = this 
    wx.scanCode({
      success: function (res) {
        that.setData({
          result: res.result
        })
      },
      fail: function (res) {
      }
    })
  },
  callPhone:function(){
    wx.makePhoneCall({
      phoneNumber: this.data.phoneNum
    })
  }
  ,
  /**
   * 条件查询 
   */
  selectStaff: function () {
 
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.selectAll();
  },
   selectAll:function(){
     var that = this;
     //人员选择
     wx.request({
       //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
       url: app.d.hostUrl + 'manager/getEmployee',
       method: 'POST',
       data: {
         tkUserId: app.d.tkUserId,
         tkUserToken: app.d.tkUserToken
       },
       header: {
         'content-type': 'application/x-www-form-urlencoded'
       },
       success: function (res) {
         var list = res.data.data;
         console.log(list);
         if (list == null) {
           list = [];
         }
         that.setData({
           infoList: list,
           // phoneNum: list.tkUserPhone
         })
         console.log(res);
       },
       fail: function () {
         wx.showToast({
           title: '服务器网络错误!',
           icon: 'loading',
           duration: 1500
         })
       }
     })
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