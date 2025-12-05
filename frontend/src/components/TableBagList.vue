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

import { Checkbox } from '@/components/ui/checkbox'
import { ShoppingBag, Store } from 'lucide-vue-next'
import { Button } from '@/components/ui/button'
import { ref } from 'vue'

const items = ref([1,2,3,4,5]);
// const items = ref([]);
</script>

<template>
    <div v-if="items.length">
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead class="w-[50px] text-center">
                        <Checkbox />
                    </TableHead>
                    <TableHead>Product</TableHead>
                    <TableHead class="w-1/6">Quantity</TableHead>
                    <TableHead class="w-1/6">Price</TableHead>
                    <TableHead class="text-right w-[200px] px-5">
                        Amount
                    </TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                <TableRow v-for="item in items" :key="item">
                    <TableCell class="font-medium text-center">
                        <Checkbox />
                    </TableCell>
                    <TableCell>
                        <Item class="px-0">
                            <ItemMedia>
                                <div class="bg-muted/50 rounded-md size-12 border"></div>
                            </ItemMedia>
                            <ItemContent>
                                <ItemTitle>Herschel Bag</ItemTitle>
                                <ItemDescription>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                </ItemDescription>
                            </ItemContent>
                        </Item>
                    </TableCell>
                    <TableCell>5</TableCell>
                    <TableCell>$19.99</TableCell>
                    <TableCell class="text-right px-5">
                        $250.00
                    </TableCell>
                </TableRow>
            </TableBody>
        </Table>
        <div class="flex py-4 justify-between align-middle">
            <Button>Proceed to Checkout</Button>
            <p class="px-5">
                Total Amount: <span class="text-2xl font-bold">$1250</span>
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
