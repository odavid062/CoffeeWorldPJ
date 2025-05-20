-- bebidas-quentes 

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(1, 'Espresso Latte suave com leite vaporizado, ideal para qualquer hora do dia.', 'https://i.imgur.com/y2yRqgP.png', 'Latte', 5.90, 3, 'bebida-quente');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(2, 'Combinação cremosa de café expresso latte, leite vaporizado e calda de chocolate.', 'https://i.imgur.com/WV9aDUf.png', 'Moccha Latte', 10.90, 8, 'bebida-quente');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(3, 'Espresso encorpado com leite vaporizado e espuma cremosa por cima', 'https://i.imgur.com/Tn1qObP.jpeg', 'Cappuccino', 6.90, 5, 'bebida-quente');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(4, 'Café quente com uma barra de chocolate mergulhada, derretendo aos poucos.', 'https://i.imgur.com/HtOWCvJ.jpeg', 'Submarino', 13.90, 10, 'bebida-quente'); --Café com uma barra de chocolate mergulhada

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(5, 'Bebida cremosa e reconfortante, feita com chocolate de verdade.', 'https://i.imgur.com/TTW6dcC.png', 'Chocolate Quente', 15.90, 12, 'bebida-quente');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(6, 'Leve e refrescante, servido em xícara transparente para realçar sua cor.', 'https://i.imgur.com/gwQrty0.png', 'Chá verde', 15.90, 12, 'bebida-quente');

-- bebidas-geladas

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(7, 'Café extraído a frio, suave e gelado, perfeito para dias quentes.', 'https://i.imgur.com/zEEZld6.png', 'Cold Brew', 5.90, 3, 'bebida-gelada');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(8, 'Espresso com leite vaporizado e cobertura de calda de caramelo.', 'https://i.imgur.com/ROLkdq6.jpeg', 'Caramelo Macchiato', 10.90, 8, 'bebida-gelada');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(9, 'Infusão aromática de frutas vermelhas, doce e levemente ácida.', 'https://i.imgur.com/EItyY5n.jpeg', 'Chá de frutas vermelhas', 6.90, 5, 'bebida-gelada');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(10, 'Cremoso e refrescante, feito com sorvete caseiro de baunilha e morangos de verdade.', 'https://i.imgur.com/aB7Vvtn.jpeg', 'Milkshake de Morango', 13.90, 10, 'bebida-gelada');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(11, 'Milkshake feito com sorvete caseiro de baunilha com o crocante do Ovomaltine.', 'https://i.imgur.com/6YG5RS3.jpeg', 'Milkshake de Ovomaltine', 15.90, 12, 'bebida-gelada');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(12, 'Expresso Latte gelado com um toque adocicado de baunilha.', 'https://i.imgur.com/rQq3BRw.png', 'Iced Latte de Baunilha', 15.90, 12, 'bebida-gelada');

-- lanches

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(13, 'Pão de queijo mineiro recheado com cremoso Catupiry.', 'https://i.imgur.com/v0Hdzz7.jpeg', 'Pão de queijo com catupiry', 5.90, 3, 'lanche');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(14, 'Frango desfiado com vegetais frescos em pão leve e saudável.', 'https://i.imgur.com/X78toxb.png', 'Sanduiche Natural de Frango', 10.90, 8, 'lanche');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(15, 'Peito de peru com alface, cenoura e maionese no pão integral.', 'https://i.imgur.com/ZNtHmCK.png', 'Sanduiche Natural de Peito de Peru', 6.90, 5, 'lanche');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(16, 'Croissant de massa folhada recheada com presunto e queijo derretido.', 'https://i.imgur.com/I7zIkEB.png', 'Croissant de Presunto e Queijo', 13.90, 10, 'lanche');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(17, 'Clássico salgado com recheio de frango com catupiry, que combina a crocância da massa com o sabor cremoso e irresistível do recheio,', 'https://i.imgur.com/qV5uURH.jpeg', 'Coxinha de Frango com catupiry', 15.90, 12, 'lanche');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(18, 'Esfiha fechada recheada com carne temperada e massa macia.', 'https://i.imgur.com/xgmBrG7.png', 'Esfiha de Carne', 15.90, 12, 'lanche');

-- sobremesas

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(19, 'Brownie úmido e denso com pedaços crocantes de nozes.', 'https://i.imgur.com/Ae6ZX9x.jpeg', 'Brownie com Nozes', 5.90, 3, 'sobremesa');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(20, 'Tradicional bolo de cenoura com cobertura generosa de chocolate.', 'https://i.imgur.com/8zv3xJo.jpeg', 'Bolo de Cenoura', 10.90, 8, 'sobremesa');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(21, 'Torta com base crocante, banana, doce de leite e chantilly.', 'https://i.imgur.com/nTr0hoE.png', 'Banoffee', 6.90, 5, 'sobremesa');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(22, 'Cheesecake cremoso com cobertura de frutas vermelhas frescas.', 'https://i.imgur.com/t4F7CO1.png', 'Cheesecake de Frutas Vermelhas', 13.90, 10, 'sobremesa');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(23, 'Massa leve com recheio cremoso e azedinho de limão e cobertura de merengue.', 'https://i.imgur.com/kxb6Zv8.jpeg', 'Torta de Limão', 15.90, 12, 'sobremesa');

INSERT INTO public.produto
(id, descricao, imagem_url, nome, preco, tempo_preparo_minutos, categoria)
VALUES(24, 'Sobremesa clássica com textura macia e calda de caramelo.', 'https://i.imgur.com/M8BwDrV.jpeg', 'Pudim de Leite Condensado', 15.90, 12, 'sobremesa');
