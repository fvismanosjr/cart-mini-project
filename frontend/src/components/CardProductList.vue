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
import { addToBag, findUser } from '@/services/user'
import type { ProductType, CartItemType } from '@/lib/types'
import { useUserStore } from '@/stores/user'
import { ref } from 'vue'

const user = useUserStore();
const products = ref<ProductType[]>([]);
const items = ref<CartItemType[]>([]);

findUser(user.user.id).then(async (response) => {
    items.value = response.cart.items;

    await getProducts().then((result: ProductType[]) => {
        products.value = result.map(product => {
            const isFound = items.value.find((item) => {
                return item.product.id == product.id
            })

            return {
                ...product,
                isInBag: isFound ? true : false
            }
        });
    })
})

const addToBagEvent = async (productId: number) => {
    await addToBag({
        productId: productId,
        quantity: 1,
    }).then(() => {
        const product = products.value.find((product) => {
            return product.id == productId
        })

        if (product) {
            product.isInBag = true;
        }
    })
}
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
                    <Button
                        variant="outline"
                        size="sm"
                        @click.prevent="addToBagEvent(product.id)"
                        v-if="!product.isInBag"
                    >
                        <ShoppingBag />
                        Add to Bag
                    </Button>
                    <Button v-else variant="secondary" size="sm" @click.prevent="$router.push({ name: 'bag' })">
                        <ShoppingBag />
                        Go to Bag
                    </Button>
                </CardFooter>
            </Card>
        </template>
    </div>
</template>
