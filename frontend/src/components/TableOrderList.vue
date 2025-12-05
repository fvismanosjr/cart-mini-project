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
    Empty,
    EmptyContent,
    EmptyDescription,
    EmptyHeader,
    EmptyMedia,
    EmptyTitle,
} from '@/components/ui/empty'

import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import { ClipboardClock, Store, X } from 'lucide-vue-next'
import { ref } from 'vue'

const orders = ref([1,2,3,4,5]);
// const orders = ref([]);
</script>

<template>
    <div v-if="orders.length">
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead class="w-[150px]">Order</TableHead>
                    <TableHead>Item Count</TableHead>
                    <TableHead>Amount</TableHead>
                    <TableHead>Date Ordered</TableHead>
                    <TableHead>Status</TableHead>
                    <TableHead class="w-[100px]"></TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                <TableRow v-for="order in orders" :key="order">
                    <TableCell>
                        1
                    </TableCell>
                    <TableCell>5</TableCell>
                    <TableCell>$19.99</TableCell>
                    <TableHead>December 05, 2025</TableHead>
                    <TableCell>
                        <Badge variant="default">Pending</Badge>
                    </TableCell>
                    <TableCell class="text-right">
                        <Button variant="secondary" size="icon-sm">
                            <X />
                        </Button>
                    </TableCell>
                </TableRow>
            </TableBody>
        </Table>
    </div>
    <Empty v-else>
        <EmptyHeader>
            <EmptyMedia variant="icon">
                <ClipboardClock />
            </EmptyMedia>
        </EmptyHeader>
        <EmptyTitle>Your Order History Is Empty</EmptyTitle>
        <EmptyDescription>Looks like you haven’t ordered anything from us yet.</EmptyDescription>
        <EmptyContent>
            <Button variant="secondary" @click.prevent="$router.push({ name: 'shop' })">
                <Store />
                Start Shopping
            </Button>
        </EmptyContent>
    </Empty>
</template>
