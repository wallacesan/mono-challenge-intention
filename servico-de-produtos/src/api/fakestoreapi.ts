interface Product {
  id: number
  title: string
  price: number
  category: string
  description: string
  image: string
  rating: Rating
}

interface Rating {
  rate: number
  count: number
}

export const getProductsApiById = (id: number) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(
        fetch(`https://fakestoreapi.com/products/${id}`)
          .then(res => res.json())
          .then(res => {
            return res as Product
          })
      )
    }, 2000)
  })
}

export const getProductsApi = (
  limit: number,
  category: string,
  sort: string
) => {
  const url = buildURL(limit, category, sort)
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(
        fetch(url)
          .then(res => res.json())
          .then(res => {
            // The response has an any type, so we need to cast
            // it to the Product type, and return it from the promise
            return res as Product[]
          })
      )
    }, 2000)
  })
}

function buildURL(limit: number, category: string, sort: string): string {
  var url = `https://fakestoreapi.com/products`

  if (category) {
    url = url + `/category/${category}`
  }

  if (limit > 0) {
    url = url + `?limit=${limit}`
  }

  if (sort && (sort.toLowerCase() == `asc` || sort.toLowerCase() == `desc`)) {
    const append = url.includes(`limit=`) ? '&' : '?'

    url = url + `${append}sort=${sort.toLowerCase()}`
  }
  return url
}
