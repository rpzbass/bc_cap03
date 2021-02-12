import React from 'react';
import { ReactComponent as Buttonseta} from 'core/assets/images/Seta.svg';
import './styles.scss';


type Props = {

    buttonText : string;

}

export const ButtonIcon = ({buttonText}:Props) => (

    <div className="d-flex">


        <button className="btn btn-primary btn-icon">

        <h5>{buttonText}</h5>

        </button>
        <div className="btn-icon-content">
            
            <Buttonseta />
        
        </div>


    </div>
);