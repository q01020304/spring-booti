<template>
<div :style='{"width":"1200px","padding":"20px","margin":"10px auto 20px","position":"relative","background":"#fff"}'>
    <el-form
	  :style='{"width":"100%","padding":"50px 0 80px","position":"relative","flexWrap":"wrap","display":"flex"}'
      class="add-update-preview"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="80px"
    >
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="订单编号" prop="dingdanbianhao">
            <el-input v-model="ruleForm.dingdanbianhao" 
                placeholder="订单编号" clearable :disabled=" false  ||ro.dingdanbianhao"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="课程编号" prop="kechengbianhao">
            <el-input v-model="ruleForm.kechengbianhao" 
                placeholder="课程编号" clearable :disabled=" false  ||ro.kechengbianhao"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="课程名称" prop="kechengmingcheng">
            <el-input v-model="ruleForm.kechengmingcheng" 
                placeholder="课程名称" clearable :disabled=" false  ||ro.kechengmingcheng"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="教师工号" prop="jiaoshigonghao">
            <el-input v-model="ruleForm.jiaoshigonghao" 
                placeholder="教师工号" clearable :disabled=" false  ||ro.jiaoshigonghao"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="教师姓名" prop="jiaoshixingming">
            <el-input v-model="ruleForm.jiaoshixingming" 
                placeholder="教师姓名" clearable :disabled=" false  ||ro.jiaoshixingming"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="学号" prop="xuehao">
            <el-input v-model="ruleForm.xuehao" 
                placeholder="学号" clearable :disabled=" false  ||ro.xuehao"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="学员姓名" prop="xueyuanxingming">
            <el-input v-model="ruleForm.xueyuanxingming" 
                placeholder="学员姓名" clearable :disabled=" false  ||ro.xueyuanxingming"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="学员手机" prop="xueyuanshouji">
            <el-input v-model="ruleForm.xueyuanshouji" 
                placeholder="学员手机" clearable :disabled=" false  ||ro.xueyuanshouji"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="作业图片" v-if="type!='cross' || (type=='cross' && !ro.zuoyetupian)" prop="zuoyetupian">
            <file-upload
            tip="点击上传作业图片"
            action="file/upload"
            :limit="3"
            :multiple="true"
            :fileUrls="ruleForm.zuoyetupian?ruleForm.zuoyetupian:''"
            @change="zuoyetupianUploadChange"
            ></file-upload>
          </el-form-item>
            <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' class="upload" v-else label="作业图片" prop="zuoyetupian">
                <img v-if="ruleForm.zuoyetupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.zuoyetupian.split(',')[0]" width="100" height="100">
                <img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.zuoyetupian.split(',')" :src="baseUrl+item" width="100" height="100">
            </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="作业名称" prop="zuoyemingcheng">
            <el-input v-model="ruleForm.zuoyemingcheng" 
                placeholder="作业名称" clearable :disabled=" false  ||ro.zuoyemingcheng"></el-input>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="作业文件" prop="zuoyewenjian">
            <file-upload
            tip="点击上传作业文件"
            action="file/upload"
            :limit="1"
			:type="3"
            :multiple="true"
            :fileUrls="ruleForm.zuoyewenjian?ruleForm.zuoyewenjian:''"
            @change="zuoyewenjianUploadChange"
            ></file-upload>
          </el-form-item>  
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}'  label="提交状态" prop="tijiaozhuangtai">
            <el-select v-model="ruleForm.tijiaozhuangtai" placeholder="请选择提交状态" :disabled=" false  ||ro.tijiaozhuangtai" >
              <el-option
                  v-for="(item,index) in tijiaozhuangtaiOptions"
                  :key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item :style='{"width":"50%","padding":"10px","margin":"0 0 10px","background":"#fff"}' label="作业内容" prop="zuoyeneirong">
            <editor 
                :style='{"minHeight":"200px","padding":"0","boxShadow":"0 0 6px #eee","margin":"0","borderColor":"#ccc","backgroundColor":"#F6F6F6","borderRadius":"0","borderWidth":"0","width":"100%","borderStyle":"solid","height":"auto"}'
                v-model="ruleForm.zuoyeneirong" 
                class="editor" 
                action="file/upload">
            </editor>
          </el-form-item>

      <el-form-item :style='{"padding":"0","margin":"0"}'>
        <el-button :style='{"border":"0","cursor":"pointer","padding":"0","margin":"0 20px 0 0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"4px","background":"#769589","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}'  type="primary" @click="onSubmit">提交</el-button>
        <el-button :style='{"border":"1px solid #769589","cursor":"pointer","padding":"0","margin":"0","outline":"none","color":"#769589","borderRadius":"4px","background":"rgba(255, 255, 255, 1)","width":"128px","lineHeight":"40px","fontSize":"14px","height":"40px"}' @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
</div>
</template>

<script>
  export default {
    data() {
	  let self = this
      return {
        id: '',
        baseUrl: '',
        ro:{
				dingdanbianhao : false,
				kechengbianhao : false,
				kechengmingcheng : false,
				jiaoshigonghao : false,
				jiaoshixingming : false,
				xuehao : false,
				xueyuanxingming : false,
				xueyuanshouji : false,
				zuoyetupian : false,
				zuoyemingcheng : false,
				zuoyewenjian : false,
				zuoyeneirong : false,
				tijiaozhuangtai : false,
        },
        type: '',
        userTableName: localStorage.getItem('UserTableName'),
        ruleForm: {
          dingdanbianhao: '',
          kechengbianhao: '',
          kechengmingcheng: '',
          jiaoshigonghao: '',
          jiaoshixingming: '',
          xuehao: '',
          xueyuanxingming: '',
          xueyuanshouji: '',
          zuoyetupian: '',
          zuoyemingcheng: '',
          zuoyewenjian: '',
          zuoyeneirong: '',
          tijiaozhuangtai: '未提交' ,
        },
        tijiaozhuangtaiOptions: [],


        rules: {
          dingdanbianhao: [
          ],
          kechengbianhao: [
          ],
          kechengmingcheng: [
          ],
          jiaoshigonghao: [
          ],
          jiaoshixingming: [
          ],
          xuehao: [
          ],
          xueyuanxingming: [
          ],
          xueyuanshouji: [
          ],
          zuoyetupian: [
          ],
          zuoyemingcheng: [
            { required: true, message: '作业名称不能为空', trigger: 'blur' },
          ],
          zuoyewenjian: [
          ],
          zuoyeneirong: [
          ],
          tijiaozhuangtai: [
            { required: true, message: '提交状态不能为空', trigger: 'blur' },
          ],
        },
		centerType: false,
      };
    },
    computed: {



    },
    components: {
    },
    created() {
		if(this.$route.query.centerType){
			this.centerType = true
		}
	  //this.bg();
      let type = this.$route.query.type ? this.$route.query.type : '';
      this.init(type);
      this.baseUrl = this.$config.baseUrl;
    },
    methods: {
      getMakeZero(s) {
          return s < 10 ? '0' + s : s;
      },
      // 下载
      download(file){
        window.open(`${file}`)
      },
      // 初始化
      init(type) {
        this.type = type;
        if(type=='cross'){
          var obj = JSON.parse(localStorage.getItem('crossObj'));
          for (var o in obj){
            if(o=='dingdanbianhao'){
              this.ruleForm.dingdanbianhao = obj[o];
              this.ro.dingdanbianhao = true;
              continue;
            }
            if(o=='kechengbianhao'){
              this.ruleForm.kechengbianhao = obj[o];
              this.ro.kechengbianhao = true;
              continue;
            }
            if(o=='kechengmingcheng'){
              this.ruleForm.kechengmingcheng = obj[o];
              this.ro.kechengmingcheng = true;
              continue;
            }
            if(o=='jiaoshigonghao'){
              this.ruleForm.jiaoshigonghao = obj[o];
              this.ro.jiaoshigonghao = true;
              continue;
            }
            if(o=='jiaoshixingming'){
              this.ruleForm.jiaoshixingming = obj[o];
              this.ro.jiaoshixingming = true;
              continue;
            }
            if(o=='xuehao'){
              this.ruleForm.xuehao = obj[o];
              this.ro.xuehao = true;
              continue;
            }
            if(o=='xueyuanxingming'){
              this.ruleForm.xueyuanxingming = obj[o];
              this.ro.xueyuanxingming = true;
              continue;
            }
            if(o=='xueyuanshouji'){
              this.ruleForm.xueyuanshouji = obj[o];
              this.ro.xueyuanshouji = true;
              continue;
            }
            if(o=='zuoyetupian'){
              this.ruleForm.zuoyetupian = obj[o].split(",")[0];
              this.ro.zuoyetupian = true;
              continue;
            }
            if(o=='zuoyemingcheng'){
              this.ruleForm.zuoyemingcheng = obj[o];
              this.ro.zuoyemingcheng = true;
              continue;
            }
            if(o=='zuoyewenjian'){
              this.ruleForm.zuoyewenjian = obj[o];
              this.ro.zuoyewenjian = true;
              continue;
            }
            if(o=='zuoyeneirong'){
              this.ruleForm.zuoyeneirong = obj[o];
              this.ro.zuoyeneirong = true;
              continue;
            }
            if(o=='tijiaozhuangtai'){
              this.ruleForm.tijiaozhuangtai = obj[o];
              this.ro.tijiaozhuangtai = true;
              continue;
            }
          }
        }else if(type=='edit'){
			this.info()
		}
        // 获取用户信息
        this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            var json = res.data.data;
          }
        });
        this.tijiaozhuangtaiOptions = "已提交,未提交".split(',')

		if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
			localStorage.removeItem('raffleType')
			setTimeout(() => {
				this.onSubmit()
			}, 300)
		}
      },

    // 多级联动参数
      // 多级联动参数
      info() {
        this.$http.get(`kehouzuoye/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.ruleForm = res.data.data;
          }
        });
      },
      // 提交
      onSubmit() {

			//更新跨表属性
			var crossuserid;
			var crossrefid;
			var crossoptnum;
			this.$refs["ruleForm"].validate(valid => {
				if(valid) {
					if(this.type=='cross'){
						var statusColumnName = localStorage.getItem('statusColumnName');
						var statusColumnValue = localStorage.getItem('statusColumnValue');
						if(statusColumnName && statusColumnName!='') {
							var obj = JSON.parse(localStorage.getItem('crossObj'));
							if(!statusColumnName.startsWith("[")) {
								for (var o in obj){
									if(o==statusColumnName){
										obj[o] = statusColumnValue;
									}
								}
								var table = localStorage.getItem('crossTable');
								this.$http.post(table+'/update', obj).then(res => {});
							} else {
								crossuserid=Number(localStorage.getItem('frontUserid'));
								crossrefid=obj['id'];
								crossoptnum=localStorage.getItem('statusColumnName');
								crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
							}
						}
					}
					if(crossrefid && crossuserid) {
						this.ruleForm.crossuserid=crossuserid;
						this.ruleForm.crossrefid=crossrefid;
						var params = {
							page: 1,
							limit: 10,
							crossuserid:crossuserid,
							crossrefid:crossrefid,
						}
						this.$http.get('kehouzuoye/list', {
							params: params
						}).then(res => {
							if(res.data.data.total>=crossoptnum) {
								this.$message({
									message: localStorage.getItem('tips'),
									type: 'error',
									duration: 1500,
								});
								return false;
							} else {
								// 跨表计算


								this.$http.post(`kehouzuoye/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(res => {
									if (res.data.code == 0) {
										this.$message({
											message: '操作成功',
											type: 'success',
											duration: 1500,
											onClose: () => {
												this.$router.go(-1);
											}
										});
									} else {
										this.$message({
											message: res.data.msg,
											type: 'error',
											duration: 1500
										});
									}
								});
							}
						});
					} else {


						this.$http.post(`kehouzuoye/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				}
			});
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		// 返回
		back() {
			this.$router.go(-1);
		},
      zuoyetupianUploadChange(fileUrls) {
          this.ruleForm.zuoyetupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
      zuoyewenjianUploadChange(fileUrls) {
          this.ruleForm.zuoyewenjian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");;
      },
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  padding: 0 10px 0 0;
	  color: #666;
	  font-weight: 500;
	  width: 80px;
	  font-size: 14px;
	  line-height: 40px;
	  text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 80px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  border: 0;
	  border-radius: 4px;
	  padding: 0 12px;
	  outline: none;
	  color: #000;
	  background: #F6F6F6;
	  width: 400px;
	  font-size: 14px;
	  height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
	  border: 0;
	  border-radius: 4px;
	  padding: 0 12px;
	  outline: none;
	  color: #000;
	  background: #F6F6F6;
	  width: 400px;
	  font-size: 14px;
	  height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  border: 0;
	  border-radius: 4px;
	  padding: 0 10px;
	  outline: none;
	  color: #000;
	  background: #F6F6F6;
	  width: 200px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  border: 0;
	  border-radius: 4px;
	  padding: 0 10px 0 30px;
	  outline: none;
	  color: #000;
	  background: #F6F6F6;
	  width: 200px;
	  font-size: 14px;
	  height: 40px;
	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  border: 1px dashed #666;
	  cursor: pointer;
	  border-radius: 6px;
	  color: #000;
	  background: #F6F6F6;
	  width: 100px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: 100px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  border: 1px dashed #666;
	  cursor: pointer;
	  border-radius: 6px;
	  color: #000;
	  background: #F6F6F6;
	  width: 100px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: 100px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  border: 1px dashed #666;
	  cursor: pointer;
	  border-radius: 6px;
	  color: #000;
	  background: #F6F6F6;
	  width: 100px;
	  font-size: 32px;
	  line-height: 100px;
	  text-align: center;
	  height: 100px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  border: 0;
	  border-radius: 4px;
	  padding: 12px;
	  outline: none;
	  color: #000;
	  background: #F6F6F6;
	  width: 400px;
	  font-size: 14px;
	  min-height: 120px;
	  height: auto;
	}
</style>
