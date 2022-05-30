<template>
  <!-- <div style="color: rgb(224, 243, 198);"> -->
  <el-table :data="tableData" style="width: 100%;color: rgb(53, 92, 189)">
    <el-table-column prop="license" label="License" width="180" />
    <el-table-column prop="1" label="#1" width="180" />
    <el-table-column prop="2" label="#2" />
    <el-table-column prop="3" label="#3" />
  </el-table>
  <!-- </div> -->
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
