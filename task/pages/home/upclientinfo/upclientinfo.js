var sourceType = [['camera'], ['album'], ['camera', 'album']]
var sizeType = [['compressed'], ['original'], ['compressed', 'original']]
var util = require('../../../utils/util.js')
var formatLocation = util.formatLocation
var app = getApp();
Page({
  /**
   * 页面的初始数据  
   */
  data: {
    datest: "",
    dates: "",
    mancheck: true,
    womancheck: false,
    num: 1,
    // 使用data数据对象设置样式名  
    minusStatus: 'disabled',
    imageList: [],
    sourceTypeIndex: 2,
    sourceType: ['拍照', '相册', '拍照或相册'],

    sizeTypeIndex: 2,
    sizeType: ['压缩', '原图', '压缩或原图'],

    countIndex: 8,
    count: [1, 2, 3, 4, 5, 6, 7, 8, 9],

    index: 0,
    //任务类型名 
    array: [],
    //任务类型id
    arrvaule: [],
    picList: [],
    //输入框的值
    inputValue: "",
    //任务类型id
    taskId: '',
    locationAddress:"",
  },
  chooseLocation: function () {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        console.log(" resGGGG  " + JSON.stringify(res))
        that.setData({
          hasLocation: true,
          location: formatLocation(res.longitude, res.latitude),
          locationAddress: res.address
        })
      }
    })
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
          that.sendTask();
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

  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value);
    var that = this;
    var values = e.detail.value;
    that.setData({
      inputValue: values
    })
    var pics = that.data.imageList;
    if (pics == null || pics.length == 0) {
      // common.toastSuccess();
      that.sendTask();
    } else {
      that.uploadimg({
        url: app.d.hostUrl + 'FileUploadServlet',//这里是你图片上传的接口
        path: pics//这里是选取的图片的地址数组
      });
    }
  },
  //上传客户信息
  sendTask: function () {
    var that = this;
    var values = that.data.inputValue;
    console.log("that.data.taskId     -------" + that.data.taskId);
    wx.request({
      //缺少用户唯一标识，现在的在服务器的控制器里有一个假id = 2
      url: app.d.hostUrl + 'client/postClientInfo',
      method: 'POST',
      data: {
        tkUserId: app.d.tkUserId,
        tkUserToken: app.d.tkUserToken,
        tk_client_name: values.tk_client_name,
        tk_client_sex: values.tk_client_sex,
        tk_client_location: values.tk_client_location,
        tk_client_location_detail: values.tk_client_location_detail,
        tk_client_goods_name: values.tk_client_goods_name,//礼物名称
        tk_client_goods_amount: that.data.num,//礼品数量
        tk_client_phone: values.tk_client_phone, //手机号
        tk_plan_id: that.data.taskId,//任务id
        tk_client_photo: that.data.picList//图片
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        wx.showToast({
          title: '上传成功!',
          icon: 'loading',
          duration: 1500,
          success: function () {
            console.log('haha');
            setTimeout(function () {
              wx.redirectTo({
                url: '../../../pages/home/myclient/myclient'
              })
            }, 1500)
          }
        })
        
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
    * 获取任务
    * 
    */
  obtainTask: function () {
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
        that.setData({
          array: that.data.array,
          arrvaule: that.data.arrvaule,
          taskId: that.data.arrvaule[0]
        });
        console.log(that.data.array);
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
  sourceTypeChange: function (e) {
    this.setData({
      sourceTypeIndex: e.detail.value
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
  /* 点击减号 */
  bindMinus: function () {
    var num = this.data.num;
    // 如果大于1时，才可以减  
    if (num > 1) {
      num--;
    }
    // 只有大于一件的时候，才能normal状态，否则disable状态  
    var minusStatus = num <= 1 ? 'disabled' : 'normal';
    // 将数值与状态写回  
    this.setData({
      num: num,
      minusStatus: minusStatus
    });
  },
  /* 点击加号 */
  bindPlus: function () {
    var num = this.data.num;
    // 不作过多考虑自增1  
    num++;
    // 只有大于一件的时候，才能normal状态，否则disable状态  
    var minusStatus = num < 1 ? 'disabled' : 'normal';
    // 将数值与状态写回  
    this.setData({
      num: num,
      minusStatus: minusStatus
    });
  },
  /* 输入框事件 */
  bindManual: function (e) {
    var num = e.detail.value;
    // 将数值与状态写回  
    this.setData({
      num: num
    });
  },
  radioCheckedChange: function (e) {
    console.log("this.data.mancheck" + this.data.mancheck);
    console.log("this.data.womancheck" + this.data.womancheck);

  },
  //  点击日期组件确定事件  
  bindDateChange: function (e) {
    console.log(e.detail.value)
    this.setData({
      dates: e.detail.value
    })
  },
  //  点击日期组件确定事件  
  bindDateChanget: function (e) {
    console.log(e.detail.value)
    this.setData({
      datest: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.obtainTask();
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