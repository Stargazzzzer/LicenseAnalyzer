<template>
  <el-table :data="tableData" style="width: 100%;color: rgb(15, 28, 60);">
    <el-table-column prop="license" label="License" width="180" />
    <el-table-column prop="1" label="#1" width="180" />
    <el-table-column prop="2" label="#2" />
    <el-table-column prop="3" label="#3" />
  </el-table>
</template>

<script>
export default {
  name: "MyTable",
  props: ["table"],
  data() {
    return {
      tableData: this.table
    };
  },
};
</script>
