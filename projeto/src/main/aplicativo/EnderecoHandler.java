package main.aplicativo;

class Endereco {
        private int cep;
        private int numCasa;
    
        public Endereco(int cep, int numCasa) {
            this.cep = cep;
            this.numCasa = numCasa;
        }
    
        public int getCep() {
            return cep;
         }
    
        public int getNumCasa() {
            return numCasa;
        }

        public void setCep(int novoCep) {
        }

        public void setNumCasa(int novoNumCasa) {
        }
    }