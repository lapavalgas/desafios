import React from 'react';
import Favorites from '../../assets/userFavorites.svg';
import './style.css';


const RepositoryToList = (props) => {
    const repository = {
        "name": "lapavalgas",
        "repositoryName": "bridgeRepository",
        "stars": "2.0M",
        "language": "java",
        "updated": "10 May"
    };


    
    
    
    return (
        <div className="repositoryToList">
            <a className="repositoryContent" href="LINK PARA O REPOSITORIO VAI VIR NOAS DADOS">
                <h3 className="repositoryNameAndOwner"><span className="repositoryOwner">{repository.name} </span><span className="repositoryName">/ {repository.repositoryName}</span></h3>
                <div className="repositoryDescription"><p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere perspiciatis natus earum placeateveniet...</p></div>
                <div className="repositoryIcons">
                    <img className="repositoryStarsIcon" src={Favorites} alt=""/><span class="repositoryStars">1.5k</span>
                    <span className="repositoryLanguage">language</span>
                    <span className="repositoryLastUpdate">updated on 10 May</span>
                </div>
            </a>
        </div>


    )
}

export default RepositoryToList;