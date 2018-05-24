// pages/myinfo/myinfo.js
//引入这个插件，使html内容自动转换成wxml内容 
 //var sentHttps = require('../../utils/common.js');
var app = getApp();
Page({ 
  /**
   * 页面的初始数据  
   */
  data: {
    imgUrl: ['../../image/home/tx.png'],
    winHeight: "",//窗口高度
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置
    MyList: [{ id: "1", pic_url: "../../image/home/qd.png", technicalSupportTittle: "签到记录", skipUrl: "/pages/myinfo/signrecord/signrecord" },
      { id: "2", pic_url: "../../image/home/db.png", technicalSupportTittle: "拓客进度", skipUrl: "/pages/myinfo/taskschedule/taskschedule" },
      { id: "3", pic_url: "../../image/home/wz.png", technicalSupportTittle: "物料申请记录", skipUrl: "/pages/myinfo/materialrecord/materialrecord" },
      { id: "4", pic_url: "../../image/home/sq.png", technicalSupportTittle: "审请报备记录", skipUrl: "/pages/myinfo/reportrecord/reportrecord" }],
    ranList:[],
    //卡片信息
    cardList: [{ id: "1", url: "/pages/home/myclient/myclient", title: "我的客户", titleClass: "mach machVivw", numIndex: "0" }, { id: "2", url: "/pages/home/thismonthbus/thismonthbus", title: "本月业务", titleClass: "mach machVivw", numIndex: "0" }, { id: "3", url: "/pages/home/group/group", title: "团队排名", titleClass: "mach machVivw", numIndex: "0" }, { id: "4", url: "/pages/home/company/company", title: "公司排名", titleClass: "mach", numIndex: "0" }],
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    userinfo: "noeLog", //  登录后
    userLog:"yeaLog", //没登录
    username:"",
    userphone:"",
    //二维码显示
    modalFlag: true,
    //客户跟进
    getClientInfoList: [],
  },
  hidd: function () {
    var that = this;
    that.setData({
      modalFlag: true
    })
  },
  modalTap: function () {
    this.setData({
      modalFlag: false
    })
  },
  modalChange2: function (e) {
    this.setData({
      modalFlag: true
    })
  },
  // 滚动切换标签样式
  switchTab: function (e) {
    this.setData({
      currentTab: e.detail.current
    });
    this.checkCor();
  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
    var cur = e.target.dataset.current;
    if (this.data.currentTaB == cur) { return false; }
    else {
      this.setData({
        currentTab: cur
      })
    }
  },

  //判断当前滚动超过一屏时，设置tab标题滚动条。
  checkCor: function () {
    if (this.data.currentTab > 4) {
      this.setData({
        scrollLeft: 300
      })
    } else {
      this.setData({
        scrollLeft: 0
      })
    }
  },
  /**
* 生命周期函数--监听页面加载
*/
  bindGetUserInfo: function (e) {
    console.log(e.detail.userInfo)
    wx.setStorageSync("userInfo", e.detail.userInfo);
    this.getUserInfo(e.detail.userInfo);
    if (e.detail.userInfo) {
      //用户按了允许授权按钮
    } else {
      //用户按了拒绝按钮
    }
  },

  getUserInfo: function (userInfo) {
    var that = this
      //调用登录接口
      wx.login({
        success: function (res) {
          var code = res.code;
          that.getUserSessionKey(code, userInfo);
        }
      });
  },
  getUserSessionKey: function (code, userInfo) {
    var that = this;
    console.log(" code " + code);
    wx.request({
      url: app.d.hostUrl + 'user/getByCode',
      method: 'POST',
      data: {
        code: code
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //--init data        
        var data = res.data;
        if (data.status == 0) {

          return false;
        }
        var dataValues = data;
        console.log(" 2 " + JSON.stringify(dataValues));
        // that.globalData.userInfo['sessionId'] = data.session_key;
        // that.globalData.userInfo['openid'] = data.openid;
        that.onLoginUser(data.openid,userInfo);
      },
      fail: function (e) {
        wx.showToast({
          title: '网络异常！err:getsessionkeys',
          duration: 2000
        });
      },
    });
  },
  onLoginUser: function (openid,userInfo) {
    var that = this;
    wx.request({
      url: app.d.hostUrl + 'user/wechatLogin',
      method: 'post',
      data: {
        nickName: userInfo.nickName,
        avatarUrl: userInfo.avatarUrl,
        openid: openid
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        //--init data        
        var data = res.data.data;
        console.log("   3  " + JSON.stringify(data));
        wx.setStorageSync("tkUserTypeId", data.tkUserTypeId)
        app.d.tkUserId = data.tkUserId;
        app.d.tkUserToken = data.tkUserToken;
        app.d.tkUserName = data.tkUserName;
        app.d.tkUserHead = data.tkUserHead;
        app.d.tkUserPhone = data.tkUserPhone;
        if (data.tkUserPhone != null && data.tkUserPhone != "") {
          console.log(" 111111 ");
          that.data.userinfo ="yeaLog";
          that.data.userLog ="noeLog";
          that.setData({
            userinfo: that.data.userinfo,
            userLog: that.data.userLog,
            username: app.d.tkUserName,
            userphone: app.d.tkUserPhone,
            imgUrl: app.d.tkUserHead
          })
          that.selectCentli();
          that.thismonthbus();
          that.towCode();
          that.treamranking();
          that.companyranking();
          that.getClientInfoList();
          that.showRandking();
        }else{
          wx.reLaunch({
            url: '../../pages/login/login',
          })
        }
      },
      fail: function (e) {
        wx.showToast({
          title: '网络异常！err:authlogin',
          duration: 2000
        });
      },
    });
  },
  onLoad: function () {
    var that=this;
    // 查看是否授权
    wx.getSetting({
      success: function (res) {
        console.log("  ######## " + JSON.stringify(res));
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              console.log("  res " + JSON.stringify(res.userInfo));
              that.getUserInfo(res.userInfo);
            },
            fail:function(e){
             
            }
          })
          console.log("  @@@@@ " + 1);
        }else{
          console.log("  $$$$$$ " + 2);
        }
      }
    })
    // that.selectCentli();
    // that.thismonthbus();
    // that.towCode();
    // that.treamranking();
    // that.companyranking();
    // that.getClientInfoList();
    // that.showRandking();
    console.log("  -===== " + app.d.tkUserPhone);
    if (app.d.tkUserPhone != null && app.d.tkUserPhone!=""){
      console.log("   aaaaaaaaaaaaaaaaa   ");
      that.data.userinfo = "yeaLog";
      that.data.userLog = "noeLog";
      that.setData({ 
        userinfo: that.data.userinfo,
        userLog: that.data.userLog,
        username: app.d.tkUserName,
        userphone: app.d.tkUserPhone,
        imgUrl: app.d.tkUserHead
      })
    }

    // that.systemInfo();
    

  },
  //显示排行榜的信息
  showRandking: function () {
    var that = this;
    //团队前三
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getPlanThree',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        if (list == null) {
          list = [];
        }
        that.setData({
          ranList: list,
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

  systemInfo:function(){
    var that = this;
    //  高度自适应
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 180;
        console.log(calc)
        that.setData({
          winHeight: calc
        });
      }
    });
  },
  /**
   * 二维码
   */
  towCode: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'user/getQCode',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data;
        that.setData({
          towCode: list.url
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

  selectCentli: function () {
    var that = this;
    //客户信息
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getClientInfo',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        if (list == null) {
          list = [];
        }
        that.setData({
          "cardList[0].numIndex": list.length
        });
        wx.setStorageSync('centliList', list);
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
   * 本月业务
   */
  thismonthbus: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getWorkNumber',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          "cardList[1].numIndex": res.data.data.length
        });
        wx.setStorageSync('thismussList', res.data.data)
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
     * 团队排名
     */
  treamranking: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getTeamIndex',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          "cardList[2].numIndex": res.data.data.length
        });
        wx.setStorageSync('thismussList', res.data.data)
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
       * 团队排名
       */
  companyranking: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getCompanyIndex',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        that.setData({
          "cardList[3].numIndex": res.data.data.length
        });
        wx.setStorageSync('thismussList', res.data.data)
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
  footerTap: app.footerTap,
  setPhotoInfo: function () {
    var that = this;
    wx.chooseImage({
      count1: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        var tempFilePaths = res.tempFilePaths;
        that.setData({ imgUrl: tempFilePaths })
      }
    })
  },
  /**客户跟进 */
  getClientInfoList: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/getClientInfo',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tkUserPhone: app.d.tkUserPhone,
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        var list = res.data.data;
        that.setData({
          getClientInfoList: list
        });
        console.log("  ^^^^  " + JSON.stringify(list));
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
