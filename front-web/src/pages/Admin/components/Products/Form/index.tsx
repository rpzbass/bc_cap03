import React from 'react';
import { BaseForm } from '../../BaseForm';
import './styles.scss';



export const Form = () => {

    return (
        
        <BaseForm title="CADASTRAR PRODUTO">
            <div className="row">
                <div className="col-6">
                    <input type="text" className="form-control"/>
                </div>   
            </div>
        </BaseForm>

    )


}