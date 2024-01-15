insert into SituacaoMatricula(id, nome, codigo) VALUES
    (1, 'Ativo', 'A01'),
    (2, 'Trancado', 'T01'),
    (3, 'Formado', 'F01'),
    (4, 'Cancelado', 'C01');

insert into Aluno (nome,matricula, hobbies, situacaoMatricula_id) VALUES
      ('Alberto', '000000001', 'Jogar Monopoly, ouvir Samba e tocar clarinete.', 1),
      ('Bianca', '000000002', 'Jogar War, ouvir Rock e tocar guitarra', 2),
      ('Carlos', '000000003', 'Jogar Banco Imobiliário, ouvir MPB e tocar violão', 3),
      ('Daniela', '000000004', 'Jogar Xadrez, ouvir Jazz e tocar piano', 4),
      ('Eduardo', '000000005', 'Jogar Gamão, ouvir Blues e tocar gaita', 1);


