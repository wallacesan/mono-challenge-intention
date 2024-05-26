import express, { Request, Response } from 'express'
import { getProducts } from '../controllers/products'
import { getProductsById } from '../controllers/products'

const router = express.Router()
router.get('/', getProducts)
router.get('/id/:id', getProductsById)
export default router
