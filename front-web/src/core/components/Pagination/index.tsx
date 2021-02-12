import React from 'react';
import './styles.scss';
import {ReactComponent as ArrowIcon } from 'core/assets/images/Seta.svg';
import { generateList }  from 'core/utils/list';


type Props = {

    totalPages :number;
    activePage: number;
    onChange: (item:number) => void;

}

export const Pagination = ({totalPages,activePage,onChange}:Props) => {

    const items = generateList(totalPages);
    const previousClass = totalPages > 0 && activePage > 0? 'page-active' : 'page-inactive';
    const nextClass = (activePage + 1) < totalPages ? 'page-active' : 'page-inactive';
    return (
        
        <div className={`pagination-container   `}>
            
            <ArrowIcon className={`icon-left  ${previousClass} `}  onClick={() => onChange(activePage - 1)}  />
            
            {items.map(item => (
                
                <div className={`pagination-item  ${item === activePage? 'active': ''}`} key={item} onClick={() => onChange(item)}>
                
                    {item + 1}
                
                </div>

            ))}
          
            <ArrowIcon className={`icon-right  ${nextClass}`} onClick={() => onChange(activePage + 1)} />
        
        </div>
    
        
    );

}



