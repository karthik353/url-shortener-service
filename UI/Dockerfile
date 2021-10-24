# Stage 1 14.18.1
FROM node:14.18.1 as build-step
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY ./ /app
RUN npm run build

# Stage 2
FROM nginx:1.20-alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build-step /app/dist/url-shortener-utility/ /usr/share/nginx/html