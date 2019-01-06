<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="dataForm.name" placeholder="名称"></el-input>
      </el-form-item>
      <el-form-item label="所属上级" prop="parentName">
        <el-popover
          ref="treeListPopover"
          placement="bottom-start"
          trigger="click">
          <el-tree
            :data="treeList"
            :props="treeListTreeProps"
            node-key="code"
            ref="treeListTree"
            @current-change="treeListTreeCurrentChangeHandle"
            :default-expand-all="true"
            :highlight-current="true"
            :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.parentName" v-popover:treeListPopover :readonly="true" placeholder="点击选择上级菜单" class="menu-list__input"></el-input>
      </el-form-item>
      <el-form-item label="keywords" prop="keywords">
        <el-input v-model="dataForm.keywords" placeholder="keywords"></el-input>
      </el-form-item>
      <el-form-item label="值" prop="value">
        <el-input v-model="dataForm.value" placeholder="value"></el-input>
      </el-form-item>
      <el-form-item label="展示" prop="show">
        <el-switch
          :active-value=1
          :inactive-value=0
          v-model="dataForm.isShow"></el-switch>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input v-model="dataForm.sort" placeholder="sort"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="dataForm.description" placeholder="组织描述"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { treeDataTranslate } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          pcode: '',
          code:'',
          name: '',
          keywords:'',
          value:'',
          isShow:false,
          sort:'',
          parentName: '',
          description: ''
        },
        dataRule: {
          parentName: [
            { required: true, message: '所属上级不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '组织名称不能为空', trigger: 'blur' }
          ]
        },
        parentName: '',
        treeList: [],
        treeListTreeProps: {
          label: 'name',
          children: 'children'
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0

        this.$http({
          url: this.$http.adornUrl('/sys/sysdictionary/select'),
          method: 'get',
          params: this.$http.adornParams()
        }).then((res) => {
          this.treeList = treeDataTranslate(res.data.treeList, 'code','pcode');
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/sys/sysdictionary/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.pcode = data.sysDictionary.pcode
                  this.dataForm.name = data.sysDictionary.name
                  this.dataForm.value = data.sysDictionary.value
                  this.dataForm.isShow = data.sysDictionary.isShow
                  this.dataForm.sort = data.sysDictionary.sort
                  this.dataForm.keywords = data.sysDictionary.keywords
                  this.dataForm.description = data.sysDictionary.description
                  this.treeListTreeSetCurrentNode()
                }
              })
            } else {
              this.dataForm.pcode = '-1'
              // 新增
              this.treeListTreeSetCurrentNode()
            }
          })
        })
      },
      // 菜单树选中
      treeListTreeCurrentChangeHandle (data, node) {
        this.dataForm.pcode = data.code
        this.dataForm.parentName = data.name
      },
      // 菜单树设置当前选中节点
      treeListTreeSetCurrentNode () {
        this.$refs.treeListTree.setCurrentKey(this.dataForm.pcode)
        this.dataForm.parentName = (this.$refs.treeListTree.getCurrentNode() || {})['name']
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/sysdictionary/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'pcode': this.dataForm.pcode,
                'name': this.dataForm.name,
                'keywords': this.dataForm.keywords,
                'isShow': this.dataForm.isShow,
                'sort': this.dataForm.sort,
                'value': this.dataForm.value,
                'description': this.dataForm.description
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
