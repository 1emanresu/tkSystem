//index.js
var app = getApp();
var util = require('../../utils/util')
var wxApi = require('../../utils/wxApi')
var wxRequest = require('../../utils/wxRequest')
import config from '../../utils/config'
 Page({
   /** 
    * 页面的初始数据
    */
   data: {
     imgUrl: ['../../image/home/tx.png'],
     winHeight: "",//窗口高度
     currentTab: 0, //预设当前项的值
     scrollLeft: 0, //tab标题的滚动条位置 
     modalFlag:true,
     clientNum:0,//我的客户
     //经理功能       
     menuList: [{ id: "1", pic_url: "../../image/home/examine.png", technicalSupportTittle: "审核审请", skipUrl:"/pages/home/audit/audit"},
       { id: "2", pic_url: "../../image/home/allocation.png", technicalSupportTittle: "分配任务", skipUrl: "/pages/home/task/task" },
       { id: "3", pic_url: " ../../image/home/resource.png", technicalSupportTittle: "资源保护", skipUrl: "/pages/home/audit/audit" },
       { id: "4", pic_url: "../../image/home/team.png", technicalSupportTittle: "团队进度", skipUrl: "/pages/home/team/team"  },
       { id: "5", pic_url: "../../image/home/personnel.png", technicalSupportTittle: "人员信息", skipUrl: "/pages/home/binding/binding" }],

     MyList: [{ id: "1", pic_url: "../../image/home/qd.png", technicalSupportTittle: "现在签到", skipUrl: "/pages/home/signin/signin" },
       { id: "2", pic_url: "../../image/home/db.png", technicalSupportTittle: "今日任务", skipUrl: "/pages/home/todaytask/todaytask" },
       { id: "3", pic_url: "../../image/home/wz.png", technicalSupportTittle: "物料申请", skipUrl: "/pages/home/applymaterial/applymaterial" },
       { id: "4", pic_url: "../../image/home/sq.png", technicalSupportTittle: "申请报备", skipUrl: "/pages/home/applyreport/applyreport" }],

       //卡片信息
     cardList: [{ id: "1", url: "/pages/home/myclient/myclient", title: "我的客户", titleClass: "mach machVivw", numIndex: "0" }, { id: "2", url: "/pages/home/thismonthbus/thismonthbus", title: "本月业务", titleClass: "mach machVivw", numIndex: "0" }, { id: "3", url: "/pages/home/group/group", title: "团队排名", titleClass: "mach machVivw", numIndex: "0" }, { id: "4", url: "/pages/home/company/company", title: "公司排名", titleClass: "mach", numIndex: "0" }],
     //本月数量
     thismussindex:0,
     idmanager:"hiddeManage",
     username: "",
     userphone: "",
     //二维码
     towCode:'',
     //客户跟进
     getClientInfoList:[],
     mask1Hidden: true,
     currentTaskList:[],
     sortSelected: "综合排序",
     selected: 0,
     sortList: [{
       sort: "综合排序",
       image: "",
     }, {
       sort: "速度最快",
       image: "",
     }, {
       sort: "评分最高",
       image: "",
     }, {
       sort: "起送价最低",
       image: "",
     }, {
       sort: "配送费最低",
       image: "",
     }],
   },
   onTapTag: function (e) {
     wx.showToast({
       title: e.currentTarget.dataset.index,
       icon: 'loading',
       duration: 1500
     })
     this.setData({
       selected: e.currentTarget.dataset.index
     });
   },
   onOverallTag: function () {
     this.setData({
       mask1Hidden: false
     })
   },
   hidd:function(){
     var that=this;
     that.setData({
       modalFlag:true
    })
   },
   modalChange2: function (e) {
     this.setData({
       modalFlag: true
     })
   },
   modalTap:function(){
     this.setData({
       modalFlag: false
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

   onLoad: function () {



     console.log("  onLoad  ");
     var that = this;
     if (app.d.tkUserId != null && app.d.tkUserId!=""){
       that.setData({
         username: app.d.tkUserName,
         userphone: app.d.tkUserPhone,
         imgUrl: app.d.tkUserHead
       })
       var tkUserTypeId = wx.getStorageSync("tkUserTypeId");
       console.log("  -----  ", tkUserTypeId);
       if (tkUserTypeId == 2) {
         that.data.idmanager = "showManage";
       } else {
         that.data.idmanager = "hiddeManage";
       }
       that.setData({
         idmanager: that.data.idmanager
       });
       wx.getSystemInfo({
         success: function (res) {
           var clientHeight = res.windowHeight,
             clientWidth = res.windowWidth,
             rpxR = 390 / clientWidth;
           var calc = clientHeight * rpxR - 25;
           // console.log(calc)
           that.setData({
             winHeight: calc
           });
         }
       });
       that.selectCentli();
       that.thismonthbus();
       that.towCode();
       that.treamranking();
       that.companyranking();
       that.getClientInfoList();
       that.currentTask();
     }else{
      
       console.log("   gggggg  ");
     }
   },
   /**
    * 二维码
    */
    towCode:function(){
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
  thismonthbus:function(){
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
  /**客户跟进 */
  getClientInfoList:function(){
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
       // console.log("  ^^^^  " + JSON.stringify(list));
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
   * 当前任务
   */
  currentTask:function(){
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'employees/getAreadyPlan',
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
        var list = res.data.data.data;
        that.setData({
          currentTaskList: list
        });
       // console.log("  ^^^^2222RRR  " + JSON.stringify(list));
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
 
   /**
    * 生命周期函数--监听页面初次渲染完成
    */
   onReady: function () {
     console.log("  onReady  ");
   },
 
   /**
    * 生命周期函数--监听页面显示
    */
   onShow: function () {
     var that = this;
     if (app.d.tkUserId != null && app.d.tkUserId != "") {
       that.setData({
         username: app.d.tkUserName,
         userphone: app.d.tkUserPhone,
         imgUrl: app.d.tkUserHead
       })
       var tkUserTypeId = wx.getStorageSync("tkUserTypeId");
       console.log("  -----  ", tkUserTypeId);
       if (tkUserTypeId == 2) {
         that.data.idmanager = "showManage";
       } else {
         that.data.idmanager = "hiddeManage";
       }
       that.setData({
         idmanager: that.data.idmanager
       });
       wx.getSystemInfo({
         success: function (res) {
           var clientHeight = res.windowHeight,
             clientWidth = res.windowWidth,
             rpxR = 390 / clientWidth;
           var calc = clientHeight * rpxR - 25;
           // console.log(calc)
           that.setData({
             winHeight: calc
           });
         }
       });
       that.selectCentli();
       that.thismonthbus();
       that.towCode();
       that.treamranking();
       that.companyranking();
       that.getClientInfoList();
     } else {

       console.log("   gggggg  ");
     }
   },
 
   /**
    * 生命周期函数--监听页面隐藏
    */
   onHide: function () {
     console.log("  onHide  ");
   },
 
   /**
    * 生命周期函数--监听页面卸载
    */
   onUnload: function () {
     console.log("  onUnload  ");
   },
 
   /**
    * 页面相关事件处理函数--监听用户下拉动作
    */
   onPullDownRefresh: function () {
     console.log("  onPullDownRefresh  ");
   },
 
   /**
    * 页面上拉触底事件的处理函数
    */
   onReachBottom: function () {
     console.log("  onReachBottom  ");
   },
 
   /**
    * 用户点击右上角分享
    */
   onShareAppMessage: function () {
     
   }
 })
