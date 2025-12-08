export interface UserType {
    id: number,
    username: string,
    role: string,
    cartId: number,
}

export interface ProductType {
    id: number,
    name: string,
    description: string,
    price: number,
    stock: number,
}

export interface ItemType {
    productId: number,
    quantity: number,
}

export interface CartItemType {
    id: number,
    product: ProductType,
    quantity: number,
    checked?: boolean,
}

export interface CartType {
    id: number,
    items: CartItemType[]
}

export interface OrderItemType {
    productId: number,
    quantity: number,
    price: number,
}

export interface OrderRequestType {
    cartId: number,
    items: OrderItemType[],
}

export interface OrderResponseType {
    id: number,
    items: [],
    status: string,
    totalAmount: number,
    createdAt: string,
}
