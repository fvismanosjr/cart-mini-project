export const formatNumberToHuman = (num: number) => {
    return new Intl.NumberFormat('en', {
        notation: "compact",
        compactDisplay: "short",
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(num);
}

export const formatNumberToPercentage = (num: number) => {
    return new Intl.NumberFormat('en', {
        style: "percent",
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(num);
}

export const formatNumberToUsdCurrency = (num: number) => {
    return new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
    }).format(num)
}

export const formatToDateTime = (num: string) => {
    return new Intl.DateTimeFormat("en-US", {
        dateStyle: "medium",
        timeStyle: "short"
    }).format(new Date(num));
}

export const canBeCanceled = (purchasedDate: string) => {
    const orderDate = new Date(purchasedDate);
    const today = new Date();

    // end date = order date + 7 days
    const cancelLimit = new Date(orderDate);
    cancelLimit.setDate(cancelLimit.getDate() + 7);

    // Check range inclusive
    return today >= orderDate && today <= cancelLimit;
}

