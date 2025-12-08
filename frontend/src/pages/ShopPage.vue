<script setup lang="ts">
import CardProductList from "@/components/CardProductList.vue";
import { ref } from "vue";

const cardProductKey = ref(0);
</script>

<template>
    <CardProductList :key="`card-product-${cardProductKey}`" @update:list="cardProductKey++"/>
</template>
