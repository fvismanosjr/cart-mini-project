<script setup lang="ts">
import {
    Card,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from '@/components/ui/card'

import { ShoppingBag } from "lucide-vue-next"
import { Badge } from '@/components/ui/badge'
import { Button } from "@/components/ui/button"
import { getProducts } from '@/services/product'
import { ref } from 'vue'
import type { ProductType } from '@/lib/types'

const products = ref<ProductType[]>([]);

getProducts().then((result) => {
    products.value = result;
})

</script>

<template>
    <div class="grid auto-rows-min gap-4 md:grid-cols-4">
        <template v-for="product in products" :key="product.id">
            <Card class="pt-0">
                <div class="bg-muted/50 rounded-md h-[200px] aspect-square w-full"></div>
                <CardHeader class="flex flex-col gap-4">
                    <Badge v-if="product.stock" variant="default">In Stock</Badge>
                    <Badge v-else variant="destructive">Out of Stock</Badge>
                    <div class="flex justify-between w-full">
                        <div>
                            <CardTitle class="mb-2">{{ product.name }}</CardTitle>
                            <CardDescription>{{ product.description }}</CardDescription>
                        </div>
                        <span class="text-xl font-medium">${{ product.price }}</span>
                    </div>
                </CardHeader>
                <CardFooter v-if="product.stock" class="flex flex-wrap gap-2 mt-auto">
                    <Button variant="outline" size="default">
                        <ShoppingBag />
                        Add to Bag
                    </Button>
                    <!-- <Button variant="destructive" size="sm">
                        <ShoppingBag />
                        Remove from Bag
                    </Button> -->
                </CardFooter>
            </Card>
        </template>
    </div>
</template>
