
export type ProductResponse = {

    content: Product[];
    totalPages:number;

}

export type Product = {

    id: number;
    name: string;
    description: string;
    price: number;
    img_url: string;
    date: string;
    categories: Category[];

}

export type Category = {
    
    id: number;
    name: string;

}


