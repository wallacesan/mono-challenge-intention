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
      fetch(`https://fakestoreapi.com/products/${id}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`Erro ao buscar produto: ${response.status}`)
          }
          return response.json()
        })
        .then((data: Product) => {
          console.log('Dados recebidos:', data)
          resolve(data)
        })
        .catch(error => {
          console.error('Erro na requisição:', error)
          resolve({ message: 'Produto não encontrado ou erro na requisição.' })
        })
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
