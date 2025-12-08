<script setup lang="ts">
import TableOrderList from "@/components/TableOrderList.vue";
import { ref } from "vue";

const tableOrderKey = ref(0);
</script>

<template>
    <TableOrderList :key="`table-order-${tableOrderKey}`" @update:list="tableOrderKey++"/>
</template>
