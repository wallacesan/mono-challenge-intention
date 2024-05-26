import express, { Request, Response, Router } from 'express'
import bodyParser from 'body-parser'
import http from 'http'
import router from './router/productrouter'

const app = express()
app.use(bodyParser.json())

const server = http.createServer(app)
server.listen(8080, () => {
  console.log('Server running on http://localhost:8080')
})

app.use('/api/products', router)
app.use('/api/products/id/{id}', router)
