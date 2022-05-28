<template>
  <label :for="type" style="font-family: Georgia, serif"> {{ label }} </label>
  <input :type="type" :id="type" v-model="inputValue" />
</template>

<script>
export default {
  name: "CustomInput",
  props: ["label", "modelValue", "type"],
  computed: {
    inputValue: {
      get() {
        return this.modelValue;
      },
      set(value) {
        this.$emit("update:modelValue", value);
      },
    },
  },
};
</script>
