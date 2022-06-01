<template>
  <div class="user_skills" style="margin-top: 20px">
    <el-table
      :data="tableData"
      style="width: 100%; color: rgb(53, 92, 189); font-weight: bold;"
      stripe
    >
      <el-table-column prop="license" label="License" />
      <el-table-column prop="1" label="#1" />
      <el-table-column prop="2" label="#2" />
      <el-table-column prop="3" label="#3" />
    </el-table>
  </div>
</template>

<script>
export default {
  name: "MyTable",
  props: ["table"],
  data() {
    return {
      tableData: this.table,
    };
  },
};
</script>

<style>
.user_skills {
  width: 50%;
  margin: auto;
}
.user_skills /deep/ .el-table--fit {
  padding: 20px;
}
.user_skills /deep/ .el-table,
.el-table__expanded-cell {
  background-color: transparent;
}

.user_skills /deep/ .el-table tr {
  background-color: transparent !important;
}
.user_skills /deep/ .el-table--enable-row-transition .el-table__body td,
.el-table .cell {
  background-color: transparent;
}
.el-table::before {
  left: 0;
  bottom: 0;
  width: 100%;
  height: 0px;
}
</style>
