import React from 'react';
import './style.css';
import Star from '../../assets/estrela.svg';
import Commit from '../../assets/time.svg';

// bridgeUser

const RepositoryDetails = (props) => {
  return (

    <div class="repositoryContent">
        <div class="repositoryInteract">
            <div class="owner"><div><span><a class="link" href="">lapavalgas</a> / <a class="link" href="">bridgegithub</a></span></div></div>
            <div class="buttons">
                <div class="border"><a class="link" href=""><span>Issues | <span class="colorWhite">21k</span></span></a></div>
                <div class="border"><a class="link" href=""><span>Stars | <span class="colorWhite">21k</span></span></a></div>
                <div class="border"><a class="link" href=""><span>Forks | <span class="colorWhite">21k</span></span></a></div>
                <div class="border"><a class="link" href=""><span>Branch | <span class="colorWhite">Master</span></span></a></div>
            </div>
        </div>

        <div class="repositoryDetails">
            <div class="repositoryDetailsHeader colorBlack">
                <div class="repositoryDetailsData"><p>Created on 17 Apr 20919</p></div>
                <div class="repositoryDetailsCommits"><img class="icon" src={Commit} /><p>230 commits</p></div>
            </div>
            <div class="repositoryTopic">
                <p class="colorBlack">About</p>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. ....</p>
            </div>
            <div class="repositoryTopic">
                <p class="colorBlack">Updated</p>
                <span>on 19 Apr 2020</span>
            </div>
            <div class="repositoryTopic">
                <p class="colorBlack">Language</p>
                <span>Python</span>
            </div>
            <div class="repositoryTopic">
                <p class="colorBlack">License</p>
                <span>MIT License</span>
            </div>
            <div class="repositoryFavorites">
                <div class="repositoryUrl">
                    <p class="colorBlack">View more on Github:</p>
                    <a href=""><span>http://github.com/lapavalgas/bridge-github-api</span></a>
                </div>
                <div class="buttonFavorites">
                    <a class="link" href="">
                        <div class="borderFavorites">
                            <img class="icon" src={Star} alt="" />
                            <span class="colorBlack">Add to Favorites</span>
                        </div>
                    </a>
                </div>
            </div>
        </div>

    </div>
    

  )
}

export default RepositoryDetails;