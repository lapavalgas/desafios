import React from 'react';
import './style.css';
import stars from '../../assets/estrela.png';

// bridgeUser


const RepositoryToList = (props) => {
    const bridgeUser = {
        "nome":"lapavalgas",
        "repository":"bridgeRepository",
        "stars":"2.0M",
        "language":"java",
        "updated":"10 May"
    };
    const {  } = props;
    return (

    <div class="repositoryToList">
    <a class="repositoryContent" href="LINK PARA O REPOSITORIO VAI VIR NOAS DADOS">
        <h3 class="repositoryNameAndOwner"><span class="repositoryOwner">{bridgeUser.nome} </span><span class="repositoryName">/ {bridgeUser.repository}</span></h3>
        <div class="repositoryDescription"><p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere perspiciatis natus earum placeateveniet...</p></div>
        <div class="repositoryIcons">
            <img class="repositoryStarsIcon" src={stars} /><span class="repositoryStars">1.5k</span>
            <span class="repositoryLanguage">language</span>
            <span class="repositoryLastUpdate">updated on 10 May</span>
        </div>
    </a>
</div>
    

  )
}

export default RepositoryToList;