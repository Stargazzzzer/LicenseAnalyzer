<template>
  <div class="user_skills" style="margin-top: 20px">
    <el-table stripe :data="tableData" style="width: 100%; color: rgb(26, 39, 70); font-weight: bold;">
      <el-table-column prop="term" label="" />
      <el-table-column prop="licensed" label="licensed" />
      <el-table-column prop="unlicensed" label="unlicensed" />
    </el-table>
  </div>
</template>

<script>
export default {
  name: "CompareTable",
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
