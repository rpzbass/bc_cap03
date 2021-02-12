import React from 'react';
import './styles.scss';
import { Link } from 'react-router-dom';
import { ReactComponent as Mainimage } from 'core/assets/images/Desenho.svg';
import { ButtonIcon } from 'core/components/ButtonIcon';


const Home = () => (

    <div className="home-container">
        <div className="row home-content card-base border-radius-20">
            <div className="col-6 home-text">
                <h1 className="text-title">Conheça o melhor<br /> catálago de produtos</h1>
                <p className="text-subtitle">

                    Ajudaremos você a encontrar os melhores <br /> produtos disponiveis no mercado.

                </p>
                <Link to="/products">
                    <ButtonIcon buttonText={"INICIE AGORA A SUA BUSCA"} />
                </Link>
            </div>
            <div className="col-6">
                <Mainimage className="main-image" />
            </div>
        </div>
    </div>



);

export default Home;


