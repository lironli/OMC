# Use a compatible Node.js version for building the Angular app
FROM node:18-alpine AS build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular application
RUN npm run build

# Use NGINX to serve the built application
FROM nginx:alpine

# Copy built Angular files from the build stage
COPY --from=build /app/dist/history-breakdown/browser /usr/share/nginx/html

# Expose port 80 for NGINX
EXPOSE 80

# Command to run NGINX
#CMD ["nginx", "-g", "daemon off;"]
