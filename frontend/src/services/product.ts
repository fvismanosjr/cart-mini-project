const PRODUCT_API_URL = "http://localhost:8080/products"

export const getProducts = async () => {
    return await fetch(PRODUCT_API_URL, {
        credentials: "include"
    }).then(async (response) => {
        return await response.json();
    })
}
