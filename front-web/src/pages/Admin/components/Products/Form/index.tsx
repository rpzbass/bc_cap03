import { makeRequest } from 'core/utils/request';
import React, { useState } from 'react';
import { BaseForm } from '../../BaseForm';
import './styles.scss';

type FormState = {

    name: string;
    price: string;
    category:string;
    description: string;

}

type FormEvent = React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>;


export const Form = () => {

    const [formData, setFormData] = useState<FormState>({
        name:'',
        price:'',
        category:'',
        description:''

    });

    const handleOnChange = (event: FormEvent) => {
        
        const name = event.target.name;
        const value = event.target.value;

        setFormData(data => ({ ...data, [name]: value}));

    }
    
    const handleSubmit = (event: React.ChangeEvent<HTMLFormElement>) => {

        event.preventDefault();
        const payload = {
            ...formData,
            img_url:'https://d229kd5ey79jzj.cloudfront.net/1313/images/1313_1_H.png',
            categories:[{id: formData.category}]
        }
   
        makeRequest({url:'/products', method: 'POST', data: payload}).then(()=> {

                setFormData({name:'', category: '', price: '', description: ''});


        });
   
    }


    return (
    <form onSubmit={handleSubmit}>
        <BaseForm title="CADASTRAR PRODUTO">
            <div className="row my-5">
                <div className="col-6">

                        <input value={formData.name} name="name" type="text" className="form-control mb-5" onChange={handleOnChange} placeholder="Nome do produto" />

                        <select value={formData.category} name="category" className="form-control mb-5" onChange={handleOnChange}>
                        
                            <option value="1">Livros</option>
                            <option value="3">Computadores</option>
                            <option value="2">Eletrônicos</option>
                            
                    
                        </select>

                        <input value={formData.price} name="price" type="text" className="form-control mb-5" onChange={handleOnChange} placeholder="Preço" />

                </div>
                <div className="col-6">
                    <textarea value={formData.description} name="description"  className="form-control" onChange={handleOnChange} cols={30} rows={10}/>
                </div>
            </div>
        </BaseForm>
    </form>
    )


}