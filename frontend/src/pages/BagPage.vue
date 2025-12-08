<script setup lang="ts">
import TableBagList from "@/components/TableBagList.vue";
import { ref } from "vue";

const tableBagKey = ref(0);
</script>

<template>
    <TableBagList :key="`table-bag-${tableBagKey}`" @update:list="tableBagKey++"/>
</template>
