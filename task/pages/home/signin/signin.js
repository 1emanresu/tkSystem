// pages/home/signin/signin.js
var sourceType = [['camera'], ['album'], ['camera', 'album']]
var sizeType = [['compressed'], ['original'], ['compressed', 'original']]
var util = require('../../../utils/util.js')
var formatLocation = util.formatLocation;
var app = getApp();
Page({

  /**
   * 页面的初始数据  
   */
  data: {
    winHeight: "",//窗口高度
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置

    imageList: [],
    sourceTypeIndex: 2,
    sourceType: ['拍照', '相册', '拍照或相册'],

    sizeTypeIndex: 2,
    sizeType: ['压缩', '原图', '压缩或原图'],
    locationAddress:"",
    countIndex: 8,
    count: [1, 2, 3, 4, 5, 6, 7, 8, 9],
    index: 0,
    //任务类型名
    array: [],
    //任务类型id
    arrvaule: [],
    //任务类型id
    taskId: '',
    //图片
    picList: [],
    //签到信息显示
    signinList:[],
  },
  /**
* 监听普通picker选择器
*/
  listenerPickerSelected: function (e) {
    //改变index值，通过setData()方法重绘界面
    console.log(" 任务 " + this.data.arrvaule[e.detail.value]);
    this.setData({
      index: e.detail.value,
      taskId: this.data.arrvaule[e.detail.value]
    });
  }, 
  /**
  * 查询已分配未签到的任务接口
  * 
  */
  selectNotTask: function () {
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'manager/getTaskToClock',
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
        for (var i = 0; i < list.length; i++) {
          that.data.array.push(list[i].tk_plan_detail_title);
          that.data.arrvaule.push(list[i].tk_plan_detail_id);
        }
       // console.log("  List11111111  " + JSON.stringify(list));
        that.setData({
          array: that.data.array,
          arrvaule: that.data.arrvaule,
          taskId: that.data.arrvaule[0]
        });
       // console.log(that.data.array);
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
    * 图片上传接口
    */
  uploadimg: function (data) {
    var that = this,
      i = data.i ? data.i : 0,//当前上传的哪张图片
      success = data.success ? data.success : 0,//上传成功的个数
      fail = data.fail ? data.fail : 0;//上传失败的个数
    wx.uploadFile({
      url: data.url,
      filePath: data.path[i],
      name: 'file',//这里根据自己的实际情况改
      formData: {
        'tkUserId': app.d.tkUserId,
        'tkUserToken': app.d.tkUserToken,
      },
      //这里是上传图片时一起上传的数据
      success: (resp) => {
        success++;//图片上传成功，图片上传成功的变量+1
        var data = resp.data;
        var datavalues = JSON.parse(data)
        console.log(" --  " + datavalues.url);
        that.data.picList.push(datavalues.url);
        that.setData({
          picList: that.data.picList
        });
        //这里可能有BUG，失败也会执行这里,所以这里应该是后台返回过来的状态码为成功时，这里的success才+1
      },
      fail: (res) => {
        fail++;//图片上传失败，图片上传失败的变量+1
        console.log('fail:' + i + "fail:" + fail);
      },
      complete: () => {
        console.log(i);
        i++;//这个图片执行完上传后，开始上传下一张
        if (i == data.path.length) {   //当图片传完时，停止调用          
          console.log('执行完毕');
          console.log('成功：' + success + " 失败：" + fail);
          that.sendsignin();
        } else {//若图片还没有传完，则继续调用函数
          console.log(i);
          data.i = i;
          data.success = success;
          data.fail = fail;
          that.uploadimg(data);
        }
      }
    });
  },

  seavSignin: function (e) {
    var that = this;
    var pics = that.data.imageList;
    if (pics == null || pics.length == 0) {
      // common.toastSuccess();
      that.sendsignin();
    } else {
      that.uploadimg({
        url: app.d.hostUrl + 'FileUploadServlet',//这里是你图片上传的接口
        path: pics//这里是选取的图片的地址数组
      });
    }
  },
  //签到
  sendsignin: function () {
    var that = this;
    console.log("that.data.taskId     -------" + that.data.taskId);
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'employees/postClockIn',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tkPlanId: that.data.taskId,
        tkPunchcardLoc: that.data.locationAddress,
        tkPunchcardPhoto: that.data.picList//图片
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        wx.showToast({
          title:res.data.msg ,
          icon: 'loading',
          duration: 1500
        })
        that.setData({
          currentTab:1
        })
        that.selectSignin();
       
        console.log(JSON.stringify(res.data));
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
   * 查询签到信息
   */
  selectSignin:function(){
    var that = this;
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'employees/getClockIn',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        // console.log(" f " + JSON.stringify(res.data.data))
        that.setData({
          signinList:res.data.data
        })
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

  chooseLocation: function () {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        console.log(res)
        that.setData({
          hasLocation: true,
          location: formatLocation(res.longitude, res.latitude),
          locationAddress: res.address
        })
      }
    })
  },
  sourceTypeChange: function (e) {
    this.setData({
      sourceTypeIndex: e.detail.value
    })
  },
  sizeTypeChange: function (e) {
    this.setData({
      sizeTypeIndex: e.detail.value
    })
  },
  countChange: function (e) {
    this.setData({
      countIndex: e.detail.value
    })
  },
  chooseImage: function () {
    var that = this
    wx.chooseImage({
      sourceType: sourceType[this.data.sourceTypeIndex],
      sizeType: sizeType[this.data.sizeTypeIndex],
      count: this.data.count[this.data.countIndex],
      success: function (res) {

        that.setData({
          imageList: res.tempFilePaths
        })
       // console.log(res.tempFilePaths)
      }
    })
  },
  previewImage: function (e) {
    var current = e.target.dataset.src
    wx.previewImage({
      current: current,
      urls: this.data.imageList
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
    if (this.data.currentTab > 2) {
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
  onLoad: function (options) {
    var that = this;
    //  高度自适应
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 85;
      //  console.log(calc)
        that.setData({
          winHeight: calc
        });
      }
    });
    wx.getLocation({
      type: 'gcj02',
      altitude:true,
      success: function (res) {
        console.log("  res @ " + JSON.stringify(res));
        var latitude = res.latitude
        var longitude = res.longitude
        var speed = res.speed
        var accuracy = res.accuracy
        console.log('   accuracy    ' + accuracy);
        that.getlocations(latitude, longitude);
      }
    })
    that.selectNotTask();
    that.selectSignin();
  },
  getlocations: function (latitude, longitude){
  var qqMapApi = 'http://api.map.baidu.com/geocoder/v2/?ak=EGLrkaW20z49iM1eI1z9ElRR7ITk1IfQ&coordtype=gcj02ll&location=' + latitude + ',' + longitude + '&output=json&pois=0';
  wx.request({
    url: qqMapApi,
    data: {},
    method: 'GET',
    success: (res) => {
      console.log(res)
      if (res.statusCode == 200 && res.data.status == 0) {
        console.log('   address144###    ' + JSON.stringify(res.data.sematic_description) );
        this.setData({
          locationAddress: res.data.result.sematic_description
        })
      }
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