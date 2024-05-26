import express from 'express'
import { getProductsApi } from '../api/fakestoreapi'
import { getProductsApiById } from '../api/fakestoreapi'

export const getProducts = async (
  req: express.Request,
  res: express.Response
) => {
  const { category, sort, limit = 0 } = req.query // A query é tudo que vem depois da interrogação //
  getProductsApi(limit as number, category as string, sort as string)
    .then(products => {
      console.log(products)
      return res.status(200).json(products)
    })
    .catch(erro => {
      console.error('Ocorreu um erro:', erro)
      return res.status(504)
    })
}

export const getProductsById = async (
  req: express.Request,
  res: express.Response
) => {
  const { id = 0 } = req.params
  getProductsApiById(id as number)
    .then(product => {
      console.log(product)
      return res.status(200).json(product)
    })
    .catch(erro => {
      console.error('Ocorreu um erro:', erro)
      return res.status(504)
    })
}
