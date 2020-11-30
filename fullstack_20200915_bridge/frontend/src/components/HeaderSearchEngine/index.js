import React from 'react';

const headerSearchEngine = (props) => {
  return (
    <div className="headerSearchEngine">
      <form action="/" >
        <input id="search" className="headerInput" type="text" name="" placeholder="Search repositories in github..." />
      </form>
    </div>
  )
}

export default headerSearchEngine;