import React from 'react';
import { useHistory } from  'react-router-dom';

export const List = () => {

    const history = useHistory();

    const handleCreate = () => {
        history.push('/admin/products/create');
    }

    return (
        
        <div className="admin-products-list">
            <button className="btn btn-primary btn-lg" onClick={handleCreate}>
                ADICIONAR
            </button>
        </div>
    )


}