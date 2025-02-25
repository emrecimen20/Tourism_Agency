PGDMP      )                |         
   emreturism    14.12    16.3 .    ,           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            -           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            .           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            /           1262    33466 
   emreturism    DATABASE        CREATE DATABASE emreturism WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE emreturism;
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            0           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    5            �            1259    33467    hotel    TABLE     �   CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_city text,
    hotel_region text,
    hotel_name text,
    hotel_phno text,
    hotel_mail text,
    hotel_star text,
    hotel_address text
);
    DROP TABLE public.hotel;
       public         heap    postgres    false    5            �            1259    33472    hotel_features    TABLE     �   CREATE TABLE public.hotel_features (
    hotel_features_id integer NOT NULL,
    hotel_features text,
    hotel_features_hotel_id integer
);
 "   DROP TABLE public.hotel_features;
       public         heap    postgres    false    5            �            1259    33477 $   hotel_features_hotel_features_id_seq    SEQUENCE     �   ALTER TABLE public.hotel_features ALTER COLUMN hotel_features_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_features_hotel_features_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    210            �            1259    33478    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    209    5            �            1259    33479    pension    TABLE     w   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    pension_hotel_id integer,
    pension_types text
);
    DROP TABLE public.pension;
       public         heap    postgres    false    5            �            1259    33484    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    213    5            �            1259    33485    reservation    TABLE     �  CREATE TABLE public.reservation (
    reserv_id integer NOT NULL,
    reserv_room_id integer,
    adult_guest_count integer,
    child_guest_count integer,
    reserv_guest_idno text,
    reserv_guest_name text,
    reserv_guest_mpno text,
    reserv_guest_mail text,
    reserv_total_prc integer,
    reserv_note text,
    checkin_date date,
    checkout_date date,
    reserv_hotel_id integer
);
    DROP TABLE public.reservation;
       public         heap    postgres    false    5            �            1259    33490    reservation_reserv_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reserv_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reserv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    215            �            1259    33491    room    TABLE       CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_season_id integer NOT NULL,
    room_pension_id integer NOT NULL,
    room_stock integer NOT NULL,
    prc_for_child integer,
    prc_for_adult integer,
    room_type text
);
    DROP TABLE public.room;
       public         heap    postgres    false    5            �            1259    33496    room_features    TABLE     �   CREATE TABLE public.room_features (
    room_feature_id integer NOT NULL,
    room_feature_room_id integer NOT NULL,
    feature_name text,
    feature_value text
);
 !   DROP TABLE public.room_features;
       public         heap    postgres    false    5            �            1259    33501 !   room_features_room_feature_id_seq    SEQUENCE     �   ALTER TABLE public.room_features ALTER COLUMN room_feature_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_features_room_feature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    218            �            1259    33502    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    217            �            1259    33503    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_hotel_id integer,
    season_start date,
    season_end date,
    season_name text
);
    DROP TABLE public.season;
       public         heap    postgres    false    5            �            1259    33508    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    221            �            1259    33509    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_role text NOT NULL,
    user_name text NOT NULL,
    user_pw text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false    5            �            1259    33514    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223    5                      0    33467    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_city, hotel_region, hotel_name, hotel_phno, hotel_mail, hotel_star, hotel_address) FROM stdin;
    public          postgres    false    209   x5                 0    33472    hotel_features 
   TABLE DATA           d   COPY public.hotel_features (hotel_features_id, hotel_features, hotel_features_hotel_id) FROM stdin;
    public          postgres    false    210   L6                 0    33479    pension 
   TABLE DATA           N   COPY public.pension (pension_id, pension_hotel_id, pension_types) FROM stdin;
    public          postgres    false    213   =7                  0    33485    reservation 
   TABLE DATA           �   COPY public.reservation (reserv_id, reserv_room_id, adult_guest_count, child_guest_count, reserv_guest_idno, reserv_guest_name, reserv_guest_mpno, reserv_guest_mail, reserv_total_prc, reserv_note, checkin_date, checkout_date, reserv_hotel_id) FROM stdin;
    public          postgres    false    215   %8       "          0    33491    room 
   TABLE DATA           �   COPY public.room (room_id, room_hotel_id, room_season_id, room_pension_id, room_stock, prc_for_child, prc_for_adult, room_type) FROM stdin;
    public          postgres    false    217   �8       #          0    33496    room_features 
   TABLE DATA           k   COPY public.room_features (room_feature_id, room_feature_room_id, feature_name, feature_value) FROM stdin;
    public          postgres    false    218   39       &          0    33503    season 
   TABLE DATA           c   COPY public.season (season_id, season_hotel_id, season_start, season_end, season_name) FROM stdin;
    public          postgres    false    221   2<       (          0    33509    user 
   TABLE DATA           H   COPY public."user" (user_id, user_role, user_name, user_pw) FROM stdin;
    public          postgres    false    223   �<       1           0    0 $   hotel_features_hotel_features_id_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.hotel_features_hotel_features_id_seq', 156, true);
          public          postgres    false    211            2           0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 25, true);
          public          postgres    false    212            3           0    0    pension_pension_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.pension_pension_id_seq', 168, true);
          public          postgres    false    214            4           0    0    reservation_reserv_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.reservation_reserv_id_seq', 14, true);
          public          postgres    false    216            5           0    0 !   room_features_room_feature_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.room_features_room_feature_id_seq', 335, true);
          public          postgres    false    219            6           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 64, true);
          public          postgres    false    220            7           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 84, true);
          public          postgres    false    222            8           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 9, true);
          public          postgres    false    224            �           2606    33516 "   hotel_features hotel_features_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.hotel_features
    ADD CONSTRAINT hotel_features_pkey PRIMARY KEY (hotel_features_id);
 L   ALTER TABLE ONLY public.hotel_features DROP CONSTRAINT hotel_features_pkey;
       public            postgres    false    210            �           2606    33518    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    209            �           2606    33520    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    213            �           2606    33522    reservation reservation_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reserv_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    215            �           2606    33524     room_features room_features_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.room_features
    ADD CONSTRAINT room_features_pkey PRIMARY KEY (room_feature_id);
 J   ALTER TABLE ONLY public.room_features DROP CONSTRAINT room_features_pkey;
       public            postgres    false    218            �           2606    33526    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    217            �           2606    33528    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    221            �           2606    33530    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    223               �   x�U�M
�0FדS�-Mu���B)�n�v���@�{��!z0Ӵ��0��3������y��y��
Q�`�Ҳ���H�&Jh��S#��n���|��7W!`HK��r�@���=2���5�qG����']S(/��,+��D��U�VВi�3�A�o����M�B��d+?t�K�S�{-d         �   x�m����0@��W�t�$v=� �<��^JwԠ6�D�~�����̎u��mxS�F*���C�.�S��,�\���]��(]UZ�[ ����^A�˹>�lfc!����i��ڍ%:b��Ɯ��L�S� �b}��M��I�E�4�oB�j�?��֑���!;�#�/�}�ؑ�}v��];��=A��z���E��NҌ�pT=M�u_�Iӹ�����p�����         �   x�m�=
�@���s�߮Z
"���Zl�deCV#krKkϠ�˙$H�߃����	|gkJ�����MU��Ό���y�L�)B����|=�c���Nx<gUq$1���&&/h�����1�X�f%��5�*QPa�9����梳9f��o���5��|F��K�z�Q-4m�d�Q_S�L{�2b�jvV����ߓ!�_��I������^,          �   x�E�A�0E׿��%�iA�nذv�f��`H,���q1�b������+1ZÁ�S�| ��:����L�#�%��]e|��Y�.=HL�����fGv`o��S�N_C�~F&��-�6�1w3�Xk���K�Rc�=�,9      "   g   x�-�;� D��0�]>��h�@"ҙx�[���4�͌g�E`�'8���E��ޔ7S�Ԉiq�������<���溜�-�!H�9��ᔘv�Ϥ�� �R�      #   �  x�}V�n�0<�_�c{)H.�9�-Т@�^Tԇ4��qV�!�G�IY⍳���]>V��ݧ_C�?��O�������i8l��uFm�߇���2�__��/w���_�u����b�0�p w�?���δ�ܷ�֑<a�ɵ*ٵ�E����Z`������*�٦B�A!�<)$���Q@��ѝk����`�Q�X��
�R�o*�"̓B�:+��@a}r���XP�ͽ2������l�C�È�	���ܮ��ސ�3b�;Б�#Q͍�B��*F,p[MG�]CTq[W�{�."b�m�ptdnOTs������hAq���������D��34�j.vn�E��K�XP�I�k��&�{�Ű���sn�K�蘸�&�޹`�+� 4}>�o�����n4�˗E���Ș��pPB�#'��$PN��Dň�fFM��ѵ�(ĕVDe뗉#�Z��#k�B+�Z�Rt1����&�4iF,lm2t�N����W���t���_�,�B%��T3�ȈyR�UJt�I�RDe�P���ıeq������m�jn��.5�)�3⶙��;�#WZ���vM0�]�Ƹ/_�"���RLt?z�,�|��I�w�KfO�t���
0�jB�Ȉ5�Y��`����E0꽙�a��6��$	�;s8�v�[cN��H���~�~�u��r�-�E�Yn��yn����洷��0����/���wL0�}��$���ֹ����l��	~      &   \   x�37�42�4202�50"(�T�ؐ3��ƣ���*��*�@**���-9���`a�P����	~�*�� 4��	&�L����� P9�      (   >   x�3�tt����t�-J�44261岀
y$�d&�rZX��qYr����G��r���q��qqq ɥ�     