<script setup lang="ts">
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from '@/components/ui/table'

import {
    Item,
    ItemContent,
    ItemDescription,
    ItemMedia,
    ItemTitle,
} from '@/components/ui/item'

import {
    Empty,
    EmptyContent,
    EmptyDescription,
    EmptyHeader,
    EmptyMedia,
    EmptyTitle,
} from '@/components/ui/empty'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import {
    ShoppingBag,
    Store,
    X,
} from 'lucide-vue-next'

import { getCart } from '@/services/product'
import { saveOrder } from '@/services/order'
import { formatNumberToUsdCurrency } from '@/helpers/Number'
import type { CartType, CartItemType } from '@/lib/types'
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'

const items = ref<CartItemType[]>([]);
const cartId = ref(0);
const router = useRouter();

getCart().then((response: CartType) => {
    cartId.value = response.id;
    items.value = response.items.map(item => ({
        ...item,
        checked: true,
    }))
})

const totalAmount = computed(() => {
    let amount = 0;

    items.value.forEach((item) => {
        amount += (item.quantity * item.product.price)
    })

    return amount;
})

const saveSelected = async () => {
    await saveOrder({
        cartId: cartId.value,
        items: items.value.map((item) => {
            return {
                productId: item.product.id,
                quantity: item.quantity,
                price: item.product.price
            }
        }),
    }).then(() => {
        router.push({
            name: "order"
        })
    })
}
</script>

<template>
    <div v-if="items.length">
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>Product</TableHead>
                    <TableHead class="w-1/6">Quantity</TableHead>
                    <TableHead class="w-1/6">Price</TableHead>
                    <TableHead class="w-1/6">Amount</TableHead>
                    <TableHead class="text-right w-[70px] px-5"></TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                <TableRow v-for="item in items" :key="item.id">
                    <TableCell>
                        <Item class="px-0">
                            <ItemMedia>
                                <div class="bg-muted/50 rounded-md size-12 border"></div>
                            </ItemMedia>
                            <ItemContent>
                                <ItemTitle>{{ item.product.name }}</ItemTitle>
                                <ItemDescription>{{ item.product.description }}</ItemDescription>
                            </ItemContent>
                        </Item>
                    </TableCell>
                    <TableCell>
                        <Input class="w-1/2 text-center border-0 outline-0 shadow-none" type="number" v-model.number="item.quantity" min="1" />
                    </TableCell>
                    <TableCell>{{ formatNumberToUsdCurrency(item.product.price) }}</TableCell>
                    <TableCell>
                        {{ formatNumberToUsdCurrency(item.quantity * item.product.price) }}
                    </TableCell>
                    <TableCell class="text-right px-5">
                        <Button variant="secondary" size="icon-sm">
                            <X />
                        </Button>
                    </TableCell>
                </TableRow>
            </TableBody>
        </Table>
        <div class="flex py-4 justify-between align-middle">
            <Button type="button" @click.prevent="saveSelected">Proceed to Checkout</Button>
            <p class="px-5">
                Total Amount: <span class="text-2xl font-bold">{{ formatNumberToUsdCurrency(totalAmount) }}</span>
            </p>
        </div>
    </div>
    <Empty v-else>
        <EmptyHeader>
            <EmptyMedia variant="icon">
                <ShoppingBag />
            </EmptyMedia>
        </EmptyHeader>
        <EmptyTitle>Your Shopping Bag Looks a Little Light</EmptyTitle>
        <EmptyDescription>Browse our collection and find something you love.</EmptyDescription>
        <EmptyContent>
            <Button variant="secondary" @click.prevent="$router.push({ name: 'shop' })">
                <Store />
                Start Shopping
            </Button>
        </EmptyContent>
    </Empty>
</template>
