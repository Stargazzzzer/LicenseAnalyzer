<template>
    <!-- <div style="color: rgb(224, 243, 198);"> -->
    <el-table :data="tableData" style="width: 100%;color: rgb(26, 39, 70)">
      <el-table-column prop="term" label=""  />
      <el-table-column prop="licensed" label="licensed"  />
      <el-table-column prop="unlicensed" label="unlicensed" />
    </el-table>
    <!-- </div> -->
  </template>
  
  <script>
  export default {
    name: "CompareTable",
    props: ["table"],
    data() {
      return {
        tableData: this.table
      };
    },
  };
  </script>
  