import React from "react"
import ContentLoader from "react-content-loader"

const ProductCardLoader = () => {
  const loaderItems = [0, 1, 2];
  return (
    <>
      {loaderItems.map(x => (
        
        <ContentLoader
        
          speed={2}
          width={240}
          height={375}
          viewBox="0 0 240 375"
          backgroundColor="#ecebeb"
          foregroundColor="#d6d2d2"

        >
          <rect x="0" y="60" rx="2" ry="2" width="240" height="375" />
        </ContentLoader>
      
      ))};

    </>
  )

}




export default ProductCardLoader;